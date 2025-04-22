package com.pragma.hogar360_microservice_visits.application.services;

import com.pragma.hogar360_microservice_visits.application.dtos.request.VisitRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;

public interface IVisitService {
    SaveResponse save(VisitRequest visitRequest);
}
