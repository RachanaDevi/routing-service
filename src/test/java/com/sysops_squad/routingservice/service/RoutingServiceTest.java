package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.fixture.ConsultantFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoutingServiceTest {

    @Test
    void shouldFindAssignedConsultant() {
        ConsultantService consultantService = mock(ConsultantService.class);
        when(consultantService.findNearestAvailableConsultant()).thenReturn(ConsultantFixture.anyConsultant());

        RoutingService routingService = new RoutingService(consultantService);

        assertThat(routingService.assignedConsultant()).isEqualTo(ConsultantFixture.anyConsultant());
    }
}