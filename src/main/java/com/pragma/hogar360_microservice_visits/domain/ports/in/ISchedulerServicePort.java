package com.pragma.hogar360_microservice_visits.domain.ports.in;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.utils.pagination.Pagination;

import java.time.LocalDateTime;

public interface ISchedulerServicePort {
    void save(SchedulerModel schedulerModel);
    Pagination<SchedulerModel> getSchedulers(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long idHouse,
            Integer page,
            Integer size);
}
