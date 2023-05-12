package com.sysops_squad.routingservice.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "consultants_availability")
public class ConsultantAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "consultant_id")
    private long consultantId;

    @Column(insertable = false, updatable = false)
    private Timestamp availableFrom;

    @Column(insertable = false, updatable = false)
    private Timestamp availableTo;

    private Boolean available;

    public ConsultantAvailability() {
    }

    public ConsultantAvailability(long id, long consultantId, Timestamp availableFrom, Timestamp availableTo, Boolean available) {
        this.id = id;
        this.consultantId = consultantId;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.available = available;
    }

    public long id() {
        return id;
    }

    public long consultantId() {
        return consultantId;
    }
}
