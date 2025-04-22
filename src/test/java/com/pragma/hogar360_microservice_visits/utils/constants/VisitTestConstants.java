package com.pragma.hogar360_microservice_visits.utils.constants;

public class VisitTestConstants {
    public static final Long VALID_ID = 1L;
    public static final Long INVALID_SCHEDULER_ID = 999L;

    public static final String VALID_EMAIL = "BUYER@EXAMPLE.COM";
    public static final String INVALID_EMAIL = "invalid.email";
    public static final String EMPTY_EMAIL = "";
    public static final String NULL_EMAIL = null;
    public static final String NORMALIZED_EMAIL = "BUYER@EXAMPLE.COM";

    public static final Long MAX_VISITS = 2L; // Debe coincidir con MAX_VISITS_SCHEDULER
    public static final Long EXCEEDED_VISITS = MAX_VISITS;
    public static final Long VALID_VISIT_COUNT = MAX_VISITS - 1L;

    public static final String SCHEDULER_NOT_FOUND_MSG = "Scheduler not found";
    public static final String VISIT_LIMIT_EXCEEDED_MSG = "Visit limit exceeded";
    public static final String EMAIL_ALREADY_EXISTS_MSG = "Email already exists";
    public static final String INVALID_EMAIL_MSG = "Invalid email format";
    public static final String EMPTY_EMAIL_MSG = "Email cannot be empty";
    public static final String NULL_SCHEDULER_MSG = "Scheduler ID cannot be null";
}
