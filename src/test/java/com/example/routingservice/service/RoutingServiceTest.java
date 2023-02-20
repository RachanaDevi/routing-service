package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import model.Ticket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RoutingServiceTest {

    @Test
    void shouldReturnAssignedConsultant() {
        RoutingService routingService = new RoutingService();

        Consultant expectedAssignedConsultant = anyConsultant();
        assertThat(routingService.assignedConsultant(anyTicket())).isEqualTo(expectedAssignedConsultant);
    }

    private Ticket anyTicket() {
        return new Ticket(1L, "Washing machine", "2022-01-02");
    }

    private Consultant anyConsultant() {
        return new Consultant(1L, "anyName", "any specialization", "any place");
    }
}