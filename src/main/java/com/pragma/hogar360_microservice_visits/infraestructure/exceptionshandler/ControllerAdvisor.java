package com.pragma.hogar360_microservice_visits.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_visits.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_visits.infraestructure.utils.constants.ExceptionConstants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EndDateCannotBeNullException.class)
    public ResponseEntity<ExceptionResponse> endDateCannotBeNullException(EndDateCannotBeNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(END_DATE_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(StartDateCannotBeNullException.class)
    public ResponseEntity<ExceptionResponse> startDateCannotBeNullException(StartDateCannotBeNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(START_DATE_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(IdHouseCannotBeNullException.class)
    public ResponseEntity<ExceptionResponse> idHouseCannotBeNullException(IdHouseCannotBeNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ID_HOUSE_CANNOT_BE_NULL_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(DateRangeAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> dateRangeAlreadyExistException(DateRangeAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(DATE_RANGE_ALREADY_EXIST_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(HouseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> houseNotFoundException(HouseNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(HOUSE_NOT_FOUND_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<ExceptionResponse> invalidDateRangeException(InvalidDateRangeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(INVALID_DATE_RANGE_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(ScheduleExceedsLimitException.class)
    public ResponseEntity<ExceptionResponse> scheduleExceedsLimitException(ScheduleExceedsLimitException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(SCHEDULE_EXCEEDS_LIMIT_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleForbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(NOT_PERMISSIONS_MESSAGE, LocalDateTime.now()));
    }
}
