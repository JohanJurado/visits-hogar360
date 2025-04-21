package com.pragma.hogar360_microservice_visits.infraestructure.utils.constants;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class ExceptionConstants {

    private ExceptionConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // page
    public static final String PAGE_NOT_FOUND_MESSAGE = "Page not found.";

    // specific validations Schedule
    public static final String END_DATE_CANNOT_BE_NULL_MESSAGE = "The end date cannot be null";
    public static final String START_DATE_CANNOT_BE_NULL_MESSAGE = "The start date cannot be null";
    public static final String ID_HOUSE_CANNOT_BE_NULL_MESSAGE = "The house cannot be null";
    public static final String DATE_RANGE_ALREADY_EXIST_MESSAGE = "The date range has already been set aside";
    public static final String HOUSE_NOT_FOUND_MESSAGE = "House not found";
    public static final String INVALID_DATE_RANGE_MESSAGE = "Invalid date range, start date cannot be greater than the end date";
    public static final String SCHEDULE_EXCEEDS_LIMIT_MESSAGE = "The schedule cannot be more than 3 months in advance";

    // token validation
    public static final String TOKEN_EXPIRED_MESSAGE = "Token has expired";
    public static final String TOKEN_MALFORMED_MESSAGE = "Token is malformed";
    public static final String TOKEN_INVALID_MESSAGE = "Token invalid";
    public static final String TOKEN_VALIDATION_FAILED_MESSAGE = "Token validation failed";

    // validate credentials
    public static final String NOT_PERMISSIONS_MESSAGE = "You don't have permissions to perform this action";

    // format exceptions of token
    public static final String CONTENT_TYPE = "application/json";
    public static final String FORMAT_MESSAGE_EXCEPTION = "{\"error\": \"%s\", \"status\": %d}";
}
