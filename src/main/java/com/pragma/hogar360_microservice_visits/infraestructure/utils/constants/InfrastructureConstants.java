package com.pragma.hogar360_microservice_visits.infraestructure.utils.constants;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class InfrastructureConstants {

    private InfrastructureConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // role constants
    public static final String HAS_ROLE_SELLER = "hasRole('SELLER')";
    public static final String ROLE_PREFIX = "ROLE_";

    // claim keys
    public static final String AUTHORITIES_CLAIM_KEY = "authorities";

    // token constants
    public static final Long TOKEN_EXPIRATION_TIME = (long) 1000 * 60 * 30;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_PREFIX = "Authorization";
    public static final Integer TOKEN_PREFIX_SIZE = 7;

    // token microservice
    public static final String SUBJECT_MICROSERVICE = "visit-schedule-service";
    public static final String AUTHORITY_MICROSERVICE = "MICROSERVICE";

}
