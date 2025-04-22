package com.pragma.hogar360_microservice_visits.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISchedulerPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.IVisitPersistencePort;
import com.pragma.hogar360_microservice_visits.infraestructure.mappers.IVisitEntityMapper;
import com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql.IVisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisitPersistenceAdapter implements IVisitPersistencePort {

    private final ISchedulerPersistencePort schedulerPersistencePort;
    private final IVisitEntityMapper visitEntityMapper;
    private final IVisitRepository visitRepository;

    @Override
    @Transactional
    public void saveWithVisitCountUpdate(VisitModel visitModel) {
        visitRepository.save(visitEntityMapper.modelToEntity(visitModel));

        visitModel.getSchedulerModel().addVisitCount();
        schedulerPersistencePort.save(visitModel.getSchedulerModel());
    }

    @Override
    public boolean existByEmailBuyerAndIdScheduler(String emailBuyer, Long idScheduler) {
        return visitRepository.existByEmailBuyerAndIdScheduler(emailBuyer, idScheduler);
    }
}
