package com.pragma.hogar360_microservice_visits.infraestructure.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="visit")
public class VisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailBuyer;

    @ManyToOne
    @JoinColumn(name = "scheduler_id")
    private SchedulerEntity schedulerEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailBuyer() {
        return emailBuyer;
    }

    public void setEmailBuyer(String emailBuyer) {
        this.emailBuyer = emailBuyer;
    }

    public SchedulerEntity getSchedulerEntity() {
        return schedulerEntity;
    }

    public void setSchedulerEntity(SchedulerEntity schedulerEntity) {
        this.schedulerEntity = schedulerEntity;
    }
}
