package com.example.routingservice.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultants_specializations")
public class ConsultantSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "consultant_id")
    private long consultantId;

    private long specializationId;

    @ManyToMany(mappedBy = "consultantsSpecializationsList", fetch = FetchType.EAGER)
    private List<Consultant> consultantsHavingSpecializations = new ArrayList<>();

    public ConsultantSpecialization() {
    }

    @Override
    public String toString() {
        return "ConsultantSpecialization{" +
                "id=" + id +
                ", consultantId=" + consultantId +
                ", specializationId=" + specializationId +
                ", consultantsHavingSpecializations=" + consultantsHavingSpecializations +
                '}';
    }
}