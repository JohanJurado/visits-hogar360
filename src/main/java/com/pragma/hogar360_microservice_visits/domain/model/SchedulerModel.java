package com.pragma.hogar360_microservice_visits.domain.model;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_visits.domain.utils.constants.DomainConstants.INITIAL_COUNT_VISITS;

public class SchedulerModel {

    private Long id;
    private String emailSeller;
    private Long idHouse;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long visitsCount;

    public SchedulerModel() {
        visitsCount = INITIAL_COUNT_VISITS;
        // insert data with setters
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }

    public Long getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(Long idHouse) {
        this.idHouse = idHouse;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(Long visitsCount) {
        this.visitsCount = visitsCount;
    }
}
