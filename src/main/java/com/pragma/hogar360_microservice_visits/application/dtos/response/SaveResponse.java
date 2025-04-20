package com.pragma.hogar360_microservice_visits.application.dtos.response;

import java.time.LocalDateTime;

public record SaveResponse(String message, LocalDateTime localDateTime) {
}
