package com.sysops_squad.routingservice.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "consultants")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String place;

    public Consultant() {
    }

    public Consultant(Long id, String name, String place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }

    public Consultant(String name, String place) {
        this.name = name;
        this.place = place;
    }

    public static Consultant noConsultant() {
        return null;
    }

    @ManyToMany
    @JoinTable(name = "consultants_availability",
            joinColumns = @JoinColumn(name = "consultant_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<ConsultantAvailability> consultantsAvailabilityList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "consultants_specializations",
            joinColumns = @JoinColumn(name = "consultant_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<ConsultantSpecialization> consultantsSpecializationsList = new ArrayList<>();

    public Long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultant that = (Consultant) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place);
    }
}