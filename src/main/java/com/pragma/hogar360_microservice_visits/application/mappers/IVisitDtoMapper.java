package com.pragma.hogar360_microservice_visits.application.mappers;

import com.pragma.hogar360_microservice_visits.application.dtos.request.VisitRequest;
import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IVisitDtoMapper {

    @Mapping(target = "schedulerModel.id", source="idScheduler")
    VisitModel requestToModel(VisitRequest visitRequest);
}
