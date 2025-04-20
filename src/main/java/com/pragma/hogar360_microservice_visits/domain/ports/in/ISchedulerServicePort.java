package com.pragma.hogar360_microservice_visits.domain.ports.in;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;

public interface ISchedulerServicePort {
    void save(SchedulerModel schedulerModel);
}
