package com.pragma.hogar360_microservice_visits.application.services.impl;

import com.pragma.hogar360_microservice_visits.application.dtos.request.SchedulerRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;
import com.pragma.hogar360_microservice_visits.application.mappers.ISchedulerDtoMapper;
import com.pragma.hogar360_microservice_visits.application.services.ISchedulerService;
import com.pragma.hogar360_microservice_visits.domain.ports.in.ISchedulerServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_visits.application.utils.ApplicationConstants.SAVE_SCHEDULER_RESPONSE;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements ISchedulerService {

    private final ISchedulerServicePort schedulerServicePort;
    private final ISchedulerDtoMapper schedulerDtoMapper;

    @Override
    public SaveResponse save(SchedulerRequest schedulerRequest) {
        schedulerServicePort.save(schedulerDtoMapper.requestToModel(schedulerRequest));
        return new SaveResponse(SAVE_SCHEDULER_RESPONSE, LocalDateTime.now());
    }
}
