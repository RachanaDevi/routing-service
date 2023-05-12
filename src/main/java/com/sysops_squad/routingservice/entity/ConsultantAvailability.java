package com.sysops_squad.routingservice.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(mappedBy = "consultantsAvailabilityList", fetch = FetchType.EAGER)
    private List<Consultant> availableConsultants = new ArrayList<>();

    public ConsultantAvailability() {
    }

    public ConsultantAvailability(long consultantId, Timestamp availableFrom, Timestamp availableTo, Boolean available) {
        this.consultantId = consultantId;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.available = available;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultantAvailability that = (ConsultantAvailability) o;
        return id == that.id && consultantId == that.consultantId && Objects.equals(availableFrom, that.availableFrom) && Objects.equals(availableTo, that.availableTo) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consultantId, availableFrom, availableTo, available);
    }

    public Boolean available() {
        return available;
    }
}
