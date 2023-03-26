package com.example.routingservice.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long specializationId;

    private String place;

    @ManyToMany
    @JoinTable(name = "consultants_availability",
            joinColumns = @JoinColumn(name = "consultant_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<ConsultantAvailability> consultantsAvailabilityList = new ArrayList<>();

    public Consultant() {
    }

    public Consultant(Long id, String name, Long specializationId, String place) {
        this.id = id;
        this.name = name;
        this.specializationId = specializationId;
        this.place = place;
    }

    public static Consultant noConsultant() {
        return null;
    }


    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specializationId='" + specializationId + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultant that = (Consultant) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(specializationId, that.specializationId) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specializationId, place);
    }
}
