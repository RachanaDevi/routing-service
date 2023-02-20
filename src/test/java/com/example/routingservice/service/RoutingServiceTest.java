package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.event.TicketCreated;
import model.Ticket;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class RoutingServiceTest {

    @Test
    void shouldReturnAssignedConsultant() {
        RoutingService routingService = new RoutingService();

        Consultant expectedAssignedConsultant = anyConsultant();
        assertThat(routingService.assignedConsultant(anyTicketCreated())).isEqualTo(expectedAssignedConsultant);
    }


    private TicketCreated anyTicketCreated() {
        return new TicketCreated(anyTicket());
    }

    private Ticket anyTicket() {
        return new Ticket(1L, "Washing machine", Timestamp.valueOf("2023-02-18 01:24:00"));
    }

    private Consultant anyConsultant() {
        return new Consultant(1L, "anyName", "any specialization", "any place");
    }
}