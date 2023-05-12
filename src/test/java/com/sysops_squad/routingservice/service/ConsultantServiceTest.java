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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    private Page<ConsultantAvailability> pagesOfConsultantAvailability() {
        return new PageImpl<>(List.of(ConsultantAvailabilityFixture.anyConsultantAvailability()), (PageRequest.of(0, 1)), 1);
    }

    @Test
    void shouldThrowConsultantUnavailableException() {
        Optional<ConsultantAvailability> emptyConsultantAvailability = Optional.empty();

        ConsultantAvailabilityRepository consultantAvailabilityRepository = mock(ConsultantAvailabilityRepository.class);
        when(consultantAvailabilityRepository.findNearestAvailableConsultant(any(), any(), any(), any())).thenReturn(Page.empty());

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