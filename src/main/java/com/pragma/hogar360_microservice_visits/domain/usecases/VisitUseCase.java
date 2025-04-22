package com.pragma.hogar360_microservice_visits.domain.usecases;

import com.pragma.hogar360_microservice_visits.domain.exceptions.EmailBuyerAlreadyExistException;
import com.pragma.hogar360_microservice_visits.domain.exceptions.SchedulerNotFoundException;
import com.pragma.hogar360_microservice_visits.domain.exceptions.SchedulerVisitCountExceedsLimitException;
import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;
import com.pragma.hogar360_microservice_visits.domain.ports.in.IVisitServicePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISchedulerPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.IVisitPersistencePort;

import java.util.Objects;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.MAX_VISITS_SCHEDULER;
import static com.pragma.hogar360_microservice_visits.domain.utils.validations.VisitValidations.validationByVisitAttributes;

public class VisitUseCase implements IVisitServicePort {

    private final IVisitPersistencePort visitPersistencePort;
    private final ISchedulerPersistencePort schedulerPersistencePort;

    public VisitUseCase(IVisitPersistencePort visitPersistencePort, ISchedulerPersistencePort schedulerPersistencePort) {
        this.visitPersistencePort = visitPersistencePort;
        this.schedulerPersistencePort = schedulerPersistencePort;
    }

    @Override
    public void save(VisitModel visitModel) {
        validationByVisitAttributes(visitModel);

        setSchedulerAttribute(visitModel);
        validateExistenceSchedulers(visitModel);

        visitPersistencePort.saveWithVisitCountUpdate(visitModel);
    }

    private void setSchedulerAttribute(VisitModel visitModel){
        visitModel.setSchedulerModel(
                schedulerPersistencePort.findById(visitModel.getSchedulerModel().getId()).orElseThrow(SchedulerNotFoundException::new)
        );
    }

    private void validateExistenceSchedulers(VisitModel visitModel){
        if (Objects.equals(visitModel.getSchedulerModel().getVisitsCount(), MAX_VISITS_SCHEDULER)){
            throw new SchedulerVisitCountExceedsLimitException();
        }
        if (visitPersistencePort.existByEmailBuyerAndIdScheduler(visitModel.getEmailBuyer(), visitModel.getSchedulerModel().getId())){
            throw new EmailBuyerAlreadyExistException();
        }
    }
}
