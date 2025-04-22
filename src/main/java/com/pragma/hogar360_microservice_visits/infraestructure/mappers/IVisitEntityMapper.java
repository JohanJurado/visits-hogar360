package com.pragma.hogar360_microservice_visits.infraestructure.mappers;

import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;
import com.pragma.hogar360_microservice_visits.infraestructure.entities.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IVisitEntityMapper {

    @Mapping(target = "schedulerEntity", source="schedulerModel")
    VisitEntity modelToEntity(VisitModel visitModel);
}
