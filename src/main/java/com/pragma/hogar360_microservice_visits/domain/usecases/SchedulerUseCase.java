package com.pragma.hogar360_microservice_visits.domain.usecases;

import com.pragma.hogar360_microservice_visits.domain.exceptions.DateRangeAlreadyExistException;
import com.pragma.hogar360_microservice_visits.domain.exceptions.HouseNotFoundException;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.ports.in.ISchedulerServicePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISchedulerPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.IHouseFeignClientPort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISecurityServicePort;

import static com.pragma.hogar360_microservice_visits.domain.utils.validations.SchedulerValidations.validationsBySchedulerAttributes;

public class SchedulerUseCase implements ISchedulerServicePort {

    private final ISchedulerPersistencePort schedulerPersistencePort;
    private final IHouseFeignClientPort houseFeignClientPort;
    private final ISecurityServicePort securityServicePort;

    public SchedulerUseCase(ISchedulerPersistencePort schedulerPersistencePort, IHouseFeignClientPort houseFeignClientPort, ISecurityServicePort securityServicePort) {
        this.schedulerPersistencePort = schedulerPersistencePort;
        this.houseFeignClientPort = houseFeignClientPort;
        this.securityServicePort = securityServicePort;
    }

    @Override
    public void save(SchedulerModel schedulerModel) {
        validationsBySchedulerAttributes(schedulerModel);
        schedulerModel.setEmailSeller(securityServicePort.getAuthenticatedEmail());

        validateExistenceOfAttributes(schedulerModel);
        schedulerPersistencePort.save(schedulerModel);
    }

    private void validateExistenceOfAttributes(SchedulerModel schedulerModel){
        if (!houseFeignClientPort.existHouseByEmailSeller(schedulerModel.getEmailSeller(), schedulerModel.getIdHouse())){
            throw new HouseNotFoundException();
        }

        if (schedulerPersistencePort.existByIdAndRangeTime(schedulerModel.getIdHouse(), schedulerModel.getStartDate(), schedulerModel.getEndDate())){
            throw new DateRangeAlreadyExistException();
        }
    }
}
