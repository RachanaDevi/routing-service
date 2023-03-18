package com.example.routingservice.entity;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultants_availability")
public class ConsultantAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "consultant_id")
    private long consultantId;

    @Column(insertable = false, updatable = false)
    private Timestamp availableFrom;

    @Column(insertable = false, updatable = false)
    private Timestamp availableTo;

    private Boolean available;

    @ManyToMany(mappedBy = "consultantsAvailabilityList", fetch = FetchType.EAGER)
    private List<Consultant> availableConsultants = new ArrayList<>();

    public ConsultantAvailability() {
    }

    public long id() {
        return id;
    }

    public long consultantId() {
        return consultantId;
    }

    @Override
    public String toString() {
        return "ConsultantAvailability{" +
                "id=" + id +
                ", consultantId=" + consultantId +
                ", availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                ", available=" + available +
                ", availableConsultants=" + availableConsultants +
                '}';
    }
}