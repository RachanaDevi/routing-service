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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsultantServiceTest {

    @Test
    void shouldFindNearestAvailableConsultant() {
        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(pagesOfConsultantAvailability());

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(Optional.of(ConsultantFixture.anyConsultant()));

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        assertThat(consultantService.findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace())).isEqualTo(ConsultantFixture.anyConsultant());
    }

    @Test
    void shouldThrowConsultantUnavailableException() {
        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(Page.empty());
        when(consultantAvailabilityRepository.findAvailableConsultant(any(), any(), any())).thenReturn(Page.empty());

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        Assertions.assertThatThrownBy(() -> consultantService.findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace())).isExactlyInstanceOf(ConsultantUnavailableException.class);
    }

    @Test
    void shouldThrowConsultantNotFoundException() {
        Optional<Consultant> emptyOptionalConsultant = Optional.empty();

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(emptyOptionalConsultant);

        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(pagesOfConsultantAvailability());

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        Assertions.assertThatThrownBy(() -> consultantService.findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace())).isExactlyInstanceOf(ConsultantNotFoundException.class);
    }

    @Test
    void shouldReturnAvailableConsultantIfNearestConsultantIsNotFound() {
        Page<ConsultantAvailability> nearestAvailableConsultantNotFound = Page.empty();
        Page<ConsultantAvailability> availableConsultant = pagesOfConsultantAvailability();

        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(nearestAvailableConsultantNotFound);
        when(consultantAvailabilityRepository.findAvailableConsultant(any(), any(), any())).thenReturn(availableConsultant);

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(Optional.of(ConsultantFixture.anyConsultant()));

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);
        consultantService.findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace());

        verify(consultantAvailabilityRepository).findAvailableConsultant(any(), any(), any());
    }

    @Test
    void shouldThrowConsultantNotFoundExceptionIfNearestNorAvailableConsultantFound() {
        Page<ConsultantAvailability> consultantAvailabilityNotFound = Page.empty();
        Optional<Consultant> emptyOptionalConsultant = Optional.empty();
        Page<ConsultantAvailability> availableConsultant = pagesOfConsultantAvailability();

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(emptyOptionalConsultant);

        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(consultantAvailabilityNotFound);
        when(consultantAvailabilityRepository.findAvailableConsultant(any(), any(), any())).thenReturn(availableConsultant);

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        Assertions.assertThatThrownBy(() -> consultantService.findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace())).isExactlyInstanceOf(ConsultantNotFoundException.class);
    }

    @Test
    void shouldUpdateConsultantAvailabilityIfNearestConsultantIsFound() {
        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(pagesOfConsultantAvailability());

        ConsultantRepository consultantRepository = mock(ConsultantRepository.class);
        when(consultantRepository.findById(any())).thenReturn(Optional.of(ConsultantFixture.anyConsultant()));

        ConsultantService consultantService = new ConsultantService(consultantAvailabilityRepository, consultantRepository);

        consultantService.findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace());

        verify(consultantAvailabilityRepository).updateAsUnavailableConsultant(any());
    }

    private Page<ConsultantAvailability> pagesOfConsultantAvailability() {
        return new PageImpl<>(List.of(ConsultantAvailabilityFixture.anyConsultantAvailability()), (PageRequest.of(0, 1)), 1);
    }

    private long anySpecializationId() {
        return 1L;
    }

    private String anyPlace() {
        return "Pune";
    }

    private Timestamp anyScheduledTimestamp() {
        return Timestamp.valueOf("2023-02-18 01:24:00");
    }
}