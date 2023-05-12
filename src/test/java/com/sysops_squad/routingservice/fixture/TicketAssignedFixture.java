package com.sysops_squad.routingservice.fixture;

import com.sysops_squad.routingservice.event.TicketAssigned;

public class TicketAssignedFixture {

    public static TicketAssigned anyTicketAssignedWithTicketId(Long ticketId) {
        return new TicketAssigned(ticketId, 1L);
    }
}
