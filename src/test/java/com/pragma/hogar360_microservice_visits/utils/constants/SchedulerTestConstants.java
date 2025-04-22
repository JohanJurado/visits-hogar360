package com.pragma.hogar360_microservice_visits.utils.constants;

import java.time.LocalDateTime;

public class SchedulerTestConstants {
    public static final Long VALID_ID = 1L;
    public static final Long INVALID_HOUSE_ID = 999L;

    public static final String SELLER_EMAIL = "seller@example.com";
    public static final String OTHER_SELLER_EMAIL = "other.seller@example.com";

    public static final LocalDateTime CURRENT_DATE = LocalDateTime.now();
    public static final LocalDateTime VALID_START_DATE = CURRENT_DATE.plusDays(1);
    public static final LocalDateTime VALID_END_DATE = VALID_START_DATE.plusHours(1);
    public static final LocalDateTime INVALID_END_DATE = VALID_START_DATE.minusHours(1);
    public static final LocalDateTime EXCEEDED_LIMIT_DATE = CURRENT_DATE.plusMonths(4);

    public static final int VALID_PAGE = 0;
    public static final int INVALID_PAGE = -1;
    public static final int PAGE_SIZE = 10;
    public static final Long MAX_VISITS = 2L;

    public static final int PLUS_DAYS = 1;
}