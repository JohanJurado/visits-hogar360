package com.pragma.hogar360_microservice_visits.infraestructure.exceptionshandler;

import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime dateTime) {
}
