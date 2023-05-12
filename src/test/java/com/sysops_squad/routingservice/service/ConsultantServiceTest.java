package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.entity.Consultant;
import com.sysops_squad.routingservice.entity.ConsultantAvailability;
import com.sysops_squad.routingservice.exception.ConsultantNotFoundException;
import com.sysops_squad.routingservice.exception.ConsultantUnavailableException;
import com.sysops_squad.routingservice.fixture.ConsultantAvailabilityFixture;
import com.sysops_squad.routingservice.fixture.ConsultantFixture;
import com.sysops_squad.routingservice.repository.ConsultantAvailabilityRepository;
import com.sysops_squad.routingservice.repository.ConsultantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsultantServiceTest {

    @Test
    void shouldFindNearestAvailableConsultant() {
        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant()).thenReturn(Optional.of(ConsultantAvailabilityFixture.anyConsultantAvailability()));

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(Optional.of(ConsultantFixture.anyConsultant()));

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        assertThat(consultantService.findNearestAvailableConsultant()).isEqualTo(ConsultantFixture.anyConsultant());
    }

    @Test
    void shouldThrowConsultantUnavailableException() {
        Optional<ConsultantAvailability> emptyConsultantAvailability = Optional.empty();

        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant()).thenReturn(emptyConsultantAvailability);

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        Assertions.assertThatThrownBy(consultantService::findNearestAvailableConsultant).isExactlyInstanceOf(ConsultantUnavailableException.class);
    }

    @Test
    void shouldThrowConsultantNotFoundException() {
        Optional<Consultant> emptyOptionalConsultant = Optional.empty();

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(emptyOptionalConsultant);

        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant()).thenReturn(Optional.of(ConsultantAvailabilityFixture.anyConsultantAvailability()));

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        Assertions.assertThatThrownBy(consultantService::findNearestAvailableConsultant).isExactlyInstanceOf(ConsultantNotFoundException.class);
    }
}