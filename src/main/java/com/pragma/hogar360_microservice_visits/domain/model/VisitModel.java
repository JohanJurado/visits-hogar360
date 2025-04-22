package com.pragma.hogar360_microservice_visits.domain.model;

public class VisitModel {

    private Long id;
    private String emailBuyer;
    private SchedulerModel schedulerModel;

    public VisitModel() {
        // insert data with setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SchedulerModel getSchedulerModel() {
        return schedulerModel;
    }

    public void setSchedulerModel(SchedulerModel schedulerModel) {
        this.schedulerModel = schedulerModel;
    }

    public String getEmailBuyer() {
        return emailBuyer;
    }

    public void setEmailBuyer(String emailBuyer) {
        this.emailBuyer = emailBuyer;
    }
}
