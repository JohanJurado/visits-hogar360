package com.pragma.hogar360_microservice_visits.application.services;

import com.pragma.hogar360_microservice_visits.application.dtos.request.SchedulerRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;

public interface ISchedulerService {
    SaveResponse save(SchedulerRequest schedulerRequest);
}
