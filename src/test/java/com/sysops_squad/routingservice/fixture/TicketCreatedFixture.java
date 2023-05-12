package com.sysops_squad.routingservice.fixture;


import com.sysops_squad.routingservice.event.TicketCreated;

public class TicketCreatedFixture {

    public static TicketCreated anyTicketCreated() {
        return anyTicketCreatedWithId(1L);
    }

    public static TicketCreated anyTicketCreatedWithId(Long id) {
        return new TicketCreated(id, 1L, 1L,
                "Washing machine not working",
                "2023-02-20 01:24:00", "Pune");
    }
}
