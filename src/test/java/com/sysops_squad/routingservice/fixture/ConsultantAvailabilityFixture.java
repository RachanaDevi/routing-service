package com.sysops_squad.routingservice.fixture;

import com.sysops_squad.routingservice.entity.ConsultantAvailability;

import java.sql.Timestamp;

public class ConsultantAvailabilityFixture {

    public static ConsultantAvailability anyConsultantAvailability() {
        return new ConsultantAvailability(1L, 1L, Timestamp.valueOf("2023-02-10 01:24:00"), Timestamp.valueOf("2023-02-21 01:24:00"), true);
    }


}
