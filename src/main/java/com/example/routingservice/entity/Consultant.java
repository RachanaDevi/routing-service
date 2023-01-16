package com.example.routingservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "consultants")
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String specialization;

    private String place;

    public Consultant() {
    }

    public Consultant(String name, String specialization, String place) {
        this.name = name;
        this.specialization = specialization;
        this.place = place;
    }
}
