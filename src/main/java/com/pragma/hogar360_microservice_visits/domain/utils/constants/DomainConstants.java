package com.pragma.hogar360_microservice_visits.domain.utils.constants;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final String UTILITY_CLASS_MESSAGE = "Utility class";

    // scheduler
    public static final Long INITIAL_COUNT_VISITS = 0L;
    public static final Long MAX_VISITS_SCHEDULER = 2L;
}
