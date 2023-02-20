package com.example.routingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultants")
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String specialization;

    private String place;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "consultants_availability",
            joinColumns = @JoinColumn(name = "consultant_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<ConsultantAvailability> availableConsultants = new ArrayList<>();

    public Consultant() {
    }

    public Consultant(Long id, String name, String specialization, String place) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.place = place;
    }


    public List<ConsultantAvailability> getAvailableConsultants() {
        return availableConsultants;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
