package com.pragma.hogar360_microservice_visits.application.mappers;

import com.pragma.hogar360_microservice_visits.application.dtos.request.SchedulerRequest;
import com.pragma.hogar360_microservice_visits.application.dtos.response.SchedulerResponse;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.utils.pagination.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISchedulerDtoMapper {

    SchedulerModel requestToModel(SchedulerRequest schedulerRequest);
    SchedulerResponse modelToResponse(SchedulerModel schedulerModel);

    default Pagination<SchedulerResponse> modelPaginationToResponsePagination(Pagination<SchedulerModel> schedulerModelPagination){
        if (schedulerModelPagination == null) {
            return null;
        }

        List<SchedulerResponse> content = schedulerModelPagination.getContent()
                .stream()
                .map(this::modelToResponse)
                .toList();

        return new Pagination<>(
                content,
                schedulerModelPagination.getPageNumber(),
                schedulerModelPagination.getPageSize(),
                schedulerModelPagination.getTotalPages(),
                schedulerModelPagination.isLast()
        );
    }
}
