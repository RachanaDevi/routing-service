package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.fixture.ConsultantFixture;
import com.sysops_squad.routingservice.fixture.TicketCreatedFixture;
import com.sysops_squad.routingservice.producer.TicketPublisher;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoutingServiceTest {

    @Test
    void shouldFindAssignedConsultant() {
        ConsultantService consultantService = mock(ConsultantService.class);
        when(consultantService.findNearestAvailableConsultant(any(), any(), any())).thenReturn(ConsultantFixture.anyConsultant());

        RoutingService routingService = new RoutingService(consultantService, mock(TicketPublisher.class));

        assertThat(routingService.assignedConsultant(TicketCreatedFixture.anyTicketCreated())).isEqualTo(ConsultantFixture.anyConsultant());
    }

    @Test
    void shouldPublishTicketAssigned() {
        ConsultantService consultantService = mock(ConsultantService.class);
        when(consultantService.findNearestAvailableConsultant(any(), any(), any())).thenReturn(ConsultantFixture.anyConsultant());

        TicketPublisher ticketAssignmentPublisher = mock(TicketPublisher.class);
        RoutingService routingService = new RoutingService(consultantService, ticketAssignmentPublisher);

        routingService.assignedConsultant(TicketCreatedFixture.anyTicketCreated());

        verify(ticketAssignmentPublisher).publish(any());
    }
}