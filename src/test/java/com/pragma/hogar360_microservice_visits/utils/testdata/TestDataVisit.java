package com.pragma.hogar360_microservice_visits.utils.testdata;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.model.VisitModel;

import static com.pragma.hogar360_microservice_visits.utils.constants.VisitTestConstants.*;

public class TestDataVisit {

    public static VisitModel getValidVisit() {
        VisitModel visit = new VisitModel();
        visit.setEmailBuyer(VALID_EMAIL);

        SchedulerModel scheduler = new SchedulerModel();
        scheduler.setId(VALID_ID);
        scheduler.setVisitsCount(VALID_VISIT_COUNT);

        visit.setSchedulerModel(scheduler);
        return visit;
    }

    public static VisitModel getVisitWithNullSchedulerId() {
        VisitModel visit = getValidVisit();
        visit.getSchedulerModel().setId(null);
        return visit;
    }

    public static VisitModel getVisitWithInvalidEmail() {
        VisitModel visit = getValidVisit();
        visit.setEmailBuyer(INVALID_EMAIL);
        return visit;
    }

    public static VisitModel getVisitWithEmptyEmail() {
        VisitModel visit = getValidVisit();
        visit.setEmailBuyer(EMPTY_EMAIL);
        return visit;
    }

    public static VisitModel getVisitWithNullEmail() {
        VisitModel visit = getValidVisit();
        visit.setEmailBuyer(NULL_EMAIL);
        return visit;
    }

    public static VisitModel getVisitWithExceededLimit() {
        VisitModel visit = getValidVisit();
        visit.getSchedulerModel().setVisitsCount(EXCEEDED_VISITS);
        return visit;
    }

    public static VisitModel getVisitWithExistingEmail() {
        return getValidVisit();
    }

    public static SchedulerModel getValidScheduler() {
        SchedulerModel scheduler = new SchedulerModel();
        scheduler.setId(VALID_ID);
        scheduler.setVisitsCount(VALID_VISIT_COUNT);
        return scheduler;
    }
}
