package com.pragma.hogar360_microservice_visits.application.dtos.response;

import java.time.LocalDateTime;

public record SchedulerResponse(
        Long id,
        String emailSeller,
        Long idHouse,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long visitsCount
) {
}
