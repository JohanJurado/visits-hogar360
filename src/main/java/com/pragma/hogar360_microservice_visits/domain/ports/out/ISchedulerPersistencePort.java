package com.pragma.hogar360_microservice_visits.domain.ports.out;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;

import java.time.LocalDateTime;

public interface ISchedulerPersistencePort {
    void save(SchedulerModel schedulerModel);
    boolean existByIdAndRangeTime(Long idHouse, LocalDateTime startDate, LocalDateTime endDate);
}
