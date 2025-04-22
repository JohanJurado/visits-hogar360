package com.pragma.hogar360_microservice_visits.domain.ports.out;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ISchedulerPersistencePort {
    void save(SchedulerModel schedulerModel);
    boolean existByIdAndRangeTime(Long idHouse, LocalDateTime startDate, LocalDateTime endDate);

    Optional<SchedulerModel> findById(Long id);

    List<SchedulerModel> findSchedulersByFilters(
            Long idHouse,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long maxVisitsScheduler);
}
