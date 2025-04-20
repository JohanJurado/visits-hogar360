package com.pragma.hogar360_microservice_visits.utils.constants;

import java.time.LocalDateTime;

public class SchedulerTestConstants {
    // IDs
    public static final Long VALID_ID = 1L;
    public static final Long INVALID_HOUSE_ID = 999L;

    // Emails
    public static final String SELLER_EMAIL = "seller@example.com";
    public static final String OTHER_SELLER_EMAIL = "other.seller@example.com";

    // Fechas
    public static final LocalDateTime CURRENT_DATE = LocalDateTime.now();
    public static final LocalDateTime VALID_START_DATE = CURRENT_DATE.plusDays(1);
    public static final LocalDateTime VALID_END_DATE = VALID_START_DATE.plusHours(1);
    public static final LocalDateTime INVALID_END_DATE = VALID_START_DATE.minusHours(1);
    public static final LocalDateTime EXCEEDED_LIMIT_DATE = CURRENT_DATE.plusMonths(4);

    // Mensajes de error
    public static final String HOUSE_NOT_FOUND_MSG = "House not found";
    public static final String DATE_RANGE_EXISTS_MSG = "Date range already exists";
    public static final String INVALID_DATE_RANGE_MSG = "Invalid date range";
    public static final String SCHEDULE_EXCEEDS_LIMIT_MSG = "Schedule exceeds limit";
    public static final String NULL_FIELD_MSG = "cannot be null";
}