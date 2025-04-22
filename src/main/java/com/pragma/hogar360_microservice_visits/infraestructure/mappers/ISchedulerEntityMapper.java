package com.pragma.hogar360_microservice_visits.infraestructure.mappers;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.infraestructure.entities.SchedulerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISchedulerEntityMapper {
    SchedulerEntity modelToEntity(SchedulerModel schedulerModel);

    SchedulerModel entityToModel(SchedulerEntity schedulerEntity);

    default Optional<SchedulerModel> entityOptionalToModelOptional(Optional<SchedulerEntity> schedulerEntityOptional){
        return schedulerEntityOptional.map(this::entityToModel);
    }

    default List<SchedulerModel> entityListToModelList(List<SchedulerEntity> schedulerEntityList){
        return schedulerEntityList.stream()
                .map(this::entityToModel)
                .toList();
    }
}
