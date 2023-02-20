package com.example.routingservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultants_availability", schema = "public" )
public class ConsultantAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long consultantId;

    @Column(insertable = false, updatable = false)
    private Timestamp availableFrom;

    @Column(insertable = false, updatable = false)
    private Timestamp availableTo;

    private Boolean available;

    @JsonIgnore
    @ManyToMany(mappedBy = "availableConsultants")
    private List<Consultant> availableConsultants = new ArrayList<>();

    public ConsultantAvailability() {
    }

    public ConsultantAvailability(Long id, List<Consultant> availableConsultants) {
        this.id = id;
        this.availableConsultants = availableConsultants;
    }

    public List<Consultant> getAvailableConsultants() {
        return availableConsultants;
    }

    public long getConsultantId() {
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
