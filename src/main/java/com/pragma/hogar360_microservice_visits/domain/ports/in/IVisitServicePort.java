package com.pragma.hogar360_microservice_visits.domain.ports.in;

import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;

public interface IVisitServicePort {
    void save(VisitModel visitModel);
}
