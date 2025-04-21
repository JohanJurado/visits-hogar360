package com.pragma.hogar360_microservice_visits.domain.utils.constants;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class PaginationConstants {

    private PaginationConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // scheduler
    public static final boolean ORDER_DESC = false;

    // global pagination
    public static final String PAGE_DEFAULT_PAGINATION = "0";
    public static final String SIZE_DEFAULT_PAGINATION = "10";
    public static final String ORDER_ASC_DEFAULT_PAGINATION = "true";

    public static final Integer SIZE_ONE_LIST_PAGINATION = 1;
    public static final Integer PAGE_INVALID_NEGATIVE = 0;
    public static final Integer PAGE_DIFF_INDEX = 1;
}
