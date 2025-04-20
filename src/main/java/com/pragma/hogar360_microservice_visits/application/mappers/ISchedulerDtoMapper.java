package com.pragma.hogar360_microservice_visits.application.mappers;

import com.pragma.hogar360_microservice_visits.application.dtos.request.SchedulerRequest;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISchedulerDtoMapper {

    SchedulerModel requestToModel(SchedulerRequest schedulerRequest);
}
