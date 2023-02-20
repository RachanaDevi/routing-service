package com.example.routingservice.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

@Entity
@Table(name = "consultants")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String specialization;

    private String place;

    public Consultant() {
    }

    public Consultant(Long id, String name, String specialization, String place) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.place = place;
    }

    public static Consultant noConsultant() {
        return null;
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
