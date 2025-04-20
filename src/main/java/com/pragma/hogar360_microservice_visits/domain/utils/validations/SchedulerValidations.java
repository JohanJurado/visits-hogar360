package com.pragma.hogar360_microservice_visits.domain.utils.validations;

import com.pragma.hogar360_microservice_visits.domain.exceptions.*;
import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_visits.domain.utils.constants.ValidationConstants.MAX_VALID_DATE_IN_MONTHS;
import static com.pragma.hogar360_microservice_visits.domain.utils.validations.GlobalValidations.validationByAttributeLObjectIsNullOrBlank;

public class SchedulerValidations {

    private SchedulerValidations() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationsBySchedulerAttributes(SchedulerModel schedulerModel) {
        validateNullAttributes(schedulerModel);
        validateRange(schedulerModel.getStartDate(), schedulerModel.getEndDate());
        validateLimitScheduler(schedulerModel.getStartDate());
    }

    private static void validateNullAttributes(SchedulerModel schedulerModel) {
        validationByAttributeLObjectIsNullOrBlank(schedulerModel.getIdHouse(), new IdHouseCannotBeNullException());
        validationByAttributeLObjectIsNullOrBlank(schedulerModel.getStartDate(), new StartDateCannotBeNullException());
        validationByAttributeLObjectIsNullOrBlank(schedulerModel.getEndDate(), new EndDateCannotBeNullException());
    }

    private static void validateRange(LocalDateTime start, LocalDateTime end){
        if (start.isAfter(end) || start.isEqual(end)){
            throw new InvalidDateRangeException();
        }
    }

    private static void validateLimitScheduler(LocalDateTime start){
        if (start.isAfter(LocalDateTime.now().plusWeeks(MAX_VALID_DATE_IN_MONTHS))) {
            throw new ScheduleExceedsLimitException();
        }
    }
}
