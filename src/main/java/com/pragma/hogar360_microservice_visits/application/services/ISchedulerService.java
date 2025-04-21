package com.pragma.hogar360_microservice_visits.application.services;

import com.pragma.hogar360_microservice_visits.application.dtos.request.SchedulerRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SchedulerResponse;
import com.pragma.hogar360_microservice_visits.domain.utils.pagination.Pagination;

import java.time.LocalDateTime;

public interface ISchedulerService {
    SaveResponse save(SchedulerRequest schedulerRequest);
    Pagination<SchedulerResponse> getSchedulers(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long idHouse,
            Integer page,
            Integer size);
}
