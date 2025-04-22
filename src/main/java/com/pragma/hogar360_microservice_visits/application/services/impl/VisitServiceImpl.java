package com.pragma.hogar360_microservice_visits.application.services.impl;

import com.pragma.hogar360_microservice_visits.application.dtos.request.VisitRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SaveResponse;
import com.pragma.hogar360_microservice_visits.application.mappers.IVisitDtoMapper;
import com.pragma.hogar360_microservice_visits.application.services.IVisitService;
import com.pragma.hogar360_microservice_visits.domain.ports.in.IVisitServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_visits.application.utils.ApplicationConstants.SAVE_VISIT_RESPONSE;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements IVisitService {

    private final IVisitServicePort visitServicePort;
    private final IVisitDtoMapper visitDtoMapper;

    @Override
    public SaveResponse save(VisitRequest visitRequest) {
        visitServicePort.save(visitDtoMapper.requestToModel(visitRequest));
        return new SaveResponse(SAVE_VISIT_RESPONSE, LocalDateTime.now());
    }
}
