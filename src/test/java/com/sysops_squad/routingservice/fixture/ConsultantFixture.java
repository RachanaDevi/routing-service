package com.sysops_squad.routingservice.fixture;

import com.sysops_squad.routingservice.entity.Consultant;

public class ConsultantFixture {

    public static Consultant anyConsultant() {
        return new Consultant(1L, "Tom", "Pune");
    }
}
