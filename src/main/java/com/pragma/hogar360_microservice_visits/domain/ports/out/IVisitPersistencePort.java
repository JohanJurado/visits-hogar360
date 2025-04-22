package com.pragma.hogar360_microservice_visits.domain.ports.out;

import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;

public interface IVisitPersistencePort {
    void saveWithVisitCountUpdate(VisitModel visitModel);
    boolean existByEmailBuyerAndIdScheduler(String emailBuyer, Long idScheduler);
}
