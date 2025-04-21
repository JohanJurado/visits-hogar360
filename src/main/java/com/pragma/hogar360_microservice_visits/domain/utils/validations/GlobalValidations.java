package com.pragma.hogar360_microservice_visits.domain.utils.validations;

import java.text.Normalizer;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.*;
import static com.pragma.hogar360_microservice_visits.domain.utils.constants.ValidationConstants.VALIDATIONS_STR_REGEX;
import static com.pragma.hogar360_microservice_visits.domain.utils.constants.ValidationConstants.VALIDATIONS_STR_REGEX_TO_BLANK;

public class GlobalValidations {

    private GlobalValidations() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByAttributeLObjectIsNullOrBlank(Object attribute, RuntimeException ex){
        if (attribute == null){
            throw ex;
        }
    }

//    public static String normalizeToUpper(String attribute) {
//        String normalized = Normalizer.normalize(attribute, Normalizer.Form.NFD);
//        normalized = normalized.replaceAll(VALIDATIONS_STR_REGEX, VALIDATIONS_STR_REGEX_TO_BLANK);
//        return normalized.toUpperCase();
//    }
}
