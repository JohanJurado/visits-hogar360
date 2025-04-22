package com.pragma.hogar360_microservice_visits.domain.utils.validations;

import com.pragma.hogar360_microservice_visits.domain.exceptions.EmailBuyerCannotBeEmptyException;
import com.pragma.hogar360_microservice_visits.domain.exceptions.EmailNotAllowedException;
import com.pragma.hogar360_microservice_visits.domain.exceptions.IdSchedulerCannotBeNullException;
import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;

import java.util.regex.Pattern;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_visits.domain.utils.constants.ValidationConstants.REGEX_VALID_EMAIL_FORMAT;
import static com.pragma.hogar360_microservice_visits.domain.utils.validations.GlobalValidations.*;

public class VisitValidations {

    private VisitValidations() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByVisitAttributes(VisitModel visitModel){
        validationByAttributeLObjectIsNullOrBlank(visitModel.getSchedulerModel().getId(), new IdSchedulerCannotBeNullException());
        visitModel.setEmailBuyer(validationByEmailAttribute(visitModel.getEmailBuyer()));
    }

    private static String validationByEmailAttribute(String emailBuyer){
        validationByAttributeIsNullOrBlank(emailBuyer, new EmailBuyerCannotBeEmptyException());
        validationByEmailFormat(emailBuyer);
        return normalizeToUpper(emailBuyer);
    }

    private static void validationByEmailFormat(String email){
        Pattern pattern = Pattern.compile(REGEX_VALID_EMAIL_FORMAT);
        if (!pattern.matcher(email).matches()){
            throw new EmailNotAllowedException();
        }
    }
}
