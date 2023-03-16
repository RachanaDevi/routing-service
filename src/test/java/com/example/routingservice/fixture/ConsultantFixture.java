package com.example.routingservice.fixture;

import com.example.routingservice.entity.Consultant;

public class ConsultantFixture {

    private static final ConsultantFixture CONSULTANT_FIXTURE_INSTANCE = new ConsultantFixture();
    private String name;
    private String specialization;
    private String place;

    public static ConsultantFixture instance() {
        return CONSULTANT_FIXTURE_INSTANCE;
    }

    public ConsultantFixture anyConsultant() {
        this.name = "Ron";
        this.specialization = "Washing machine";
        this.place = "Pune";
        return this;
    }

    public ConsultantFixture withSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public ConsultantFixture withPlace(String place) {
        this.place = place;
        return this;
    }

    public Consultant build() {
        return new Consultant(1l, this.name, this.specialization, this.place);
    }

}
