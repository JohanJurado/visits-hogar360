package com.pragma.hogar360_microservice_visits.application.dtos.request;

import java.time.LocalDateTime;

public record SchedulerRequest(
        Long idHouse,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
