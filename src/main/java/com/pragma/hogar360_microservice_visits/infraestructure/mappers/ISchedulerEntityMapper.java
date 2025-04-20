package com.pragma.hogar360_microservice_visits.infraestructure.mappers;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.infraestructure.entities.SchedulerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISchedulerEntityMapper {
    SchedulerEntity modelToEntity(SchedulerModel schedulerModel);
}
