package com.pragma.hogar360_microservice_visits.utils.testdata;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;

import java.util.List;

import static com.pragma.hogar360_microservice_visits.utils.constants.SchedulerTestConstants.*;

public class TestDataScheduler {

    public static SchedulerModel getValidScheduler() {
        SchedulerModel scheduler = new SchedulerModel();
        scheduler.setIdHouse(VALID_ID);
        scheduler.setStartDate(VALID_START_DATE);
        scheduler.setEndDate(VALID_END_DATE);
        scheduler.setEmailSeller(SELLER_EMAIL);
        return scheduler;
    }

    public static SchedulerModel getSchedulerWithInvalidRange() {
        SchedulerModel scheduler = getValidScheduler();
        scheduler.setEndDate(INVALID_END_DATE);
        return scheduler;
    }

    public static SchedulerModel getSchedulerWithExceededLimit() {
        SchedulerModel scheduler = getValidScheduler();
        scheduler.setStartDate(EXCEEDED_LIMIT_DATE);
        scheduler.setEndDate(EXCEEDED_LIMIT_DATE.plusHours(1));
        return scheduler;
    }

    public static SchedulerModel getSchedulerWithNullHouseId() {
        SchedulerModel scheduler = getValidScheduler();
        scheduler.setIdHouse(null);
        return scheduler;
    }

    public static SchedulerModel getSchedulerWithNullStartDate() {
        SchedulerModel scheduler = getValidScheduler();
        scheduler.setStartDate(null);
        return scheduler;
    }

    public static SchedulerModel getSchedulerWithNullEndDate() {
        SchedulerModel scheduler = getValidScheduler();
        scheduler.setEndDate(null);
        return scheduler;
    }

    public static SchedulerModel getSchedulerWithExistingRange() {
        return getValidScheduler();
    }

    public static List<SchedulerModel> getSchedulerList() {
        SchedulerModel schedulerModel2 = getValidScheduler();
        schedulerModel2.setStartDate(schedulerModel2.getStartDate().plusDays(PLUS_DAYS));
        schedulerModel2.setEndDate(schedulerModel2.getEndDate().plusDays(PLUS_DAYS));

        SchedulerModel schedulerModel3 = getValidScheduler();
        schedulerModel3.setStartDate(schedulerModel2.getStartDate().plusDays(PLUS_DAYS));
        schedulerModel3.setEndDate(schedulerModel3.getStartDate().plusDays(PLUS_DAYS));
        return List.of(
                getValidScheduler(),
                schedulerModel2,
                schedulerModel3
        );
    }

    public static List<SchedulerModel> getEmptySchedulerList() {
        return List.of();
    }
}
