package com.sysops_squad.routingservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consultants_specializations")
public class ConsultantSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "consultant_id")
    private long consultantId;

    private long specializationId;

    @ManyToMany(mappedBy = "consultantsSpecializationsList", fetch = FetchType.EAGER)
    private List<Consultant> consultantsHavingSpecializations = new ArrayList<>();

    public ConsultantSpecialization() {
    }
}