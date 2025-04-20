package com.pragma.hogar360_microservice_visits.domain.utils.constants;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class ValidationConstants {

    private ValidationConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final String VALIDATIONS_STR_REGEX = "\\p{M}";
    public static final String VALIDATIONS_STR_REGEX_TO_BLANK = "";

    public static final Long MAX_VALID_DATE_IN_MONTHS = 3L;
}
