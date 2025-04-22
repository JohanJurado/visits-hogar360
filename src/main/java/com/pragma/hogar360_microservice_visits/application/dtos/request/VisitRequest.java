package com.pragma.hogar360_microservice_visits.application.dtos.request;

public record VisitRequest(
        String emailBuyer,
        Long idScheduler
) {
}
