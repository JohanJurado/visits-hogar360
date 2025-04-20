package com.pragma.hogar360_microservice_visits.application.utils;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class ApplicationConstants {

    private ApplicationConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final String SAVE_SCHEDULER_RESPONSE = "Visit scheduler have been successfully added";
}
