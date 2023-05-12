package com.sysops_squad.routingservice.repository;

import com.sysops_squad.routingservice.entity.ConsultantAvailability;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ConsultantAvailabilityRepositoryIntegrationTest {

    @Autowired
    private ConsultantAvailabilityRepository consultantAvailabilityRepository;

    @Test
    @Transactional
    void shouldReturnNearestAvailableConsultant() {
        long consultantAvailabilityId = 2L;

        Page<ConsultantAvailability> nearestAvailableConsultant = consultantAvailabilityRepository
                .findNearestAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), anyPlace(), pagesOfConsultantAvailability());

        assertThat(nearestAvailableConsultant.getTotalPages()).isEqualTo(2);
        assertThat(nearestAvailableConsultant.get().toList()).usingRecursiveComparison()
                .ignoringFields("availableConsultants")
                .isEqualTo(List.of(
                        new ConsultantAvailability(consultantAvailabilityId, 1L,
                                Timestamp.valueOf("2023-02-11 00:00:00.0"),
                                Timestamp.valueOf("2023-02-22 01:24:23.0"),
                                true)));
    }

    @Test
    @Transactional
    void shouldReturnAvailableConsultant() {
        long consultantAvailabilityId = 2L;

        Page<ConsultantAvailability> nearestAvailableConsultant = consultantAvailabilityRepository
                .findAvailableConsultant(anyScheduledTimestamp(), anySpecializationId(), pagesOfConsultantAvailability());

        assertThat(nearestAvailableConsultant.getTotalPages()).isEqualTo(2);
        assertThat(nearestAvailableConsultant.get().toList()).usingRecursiveComparison()
                .ignoringFields("availableConsultants")
                .isEqualTo(List.of(
                        new ConsultantAvailability(consultantAvailabilityId, 1L,
                                Timestamp.valueOf("2023-02-11 00:00:00.0"),
                                Timestamp.valueOf("2023-02-22 01:24:23.0"),
                                true)));
    }

    @Test
    @Transactional
    @Rollback
    void shouldUpdateConsultantAvailabilityAsFalse() {
        ConsultantAvailability consultantAvailability = consultantAvailabilityRepository.save(new ConsultantAvailability(1L,
                Timestamp.valueOf("2023-02-11 00:00:00.0"),
                Timestamp.valueOf("2023-02-22 01:24:23.0"),
                true));

        consultantAvailabilityRepository
                .updateAsUnavailableConsultant(consultantAvailability.id());

        Optional<ConsultantAvailability> consultantAvailabilityOptional = consultantAvailabilityRepository.findById(consultantAvailability.id());

        assertThat(consultantAvailabilityOptional.get().available()).isFalse();
    }

    private long anySpecializationId() {
        return 1L;
    }

    private String anyPlace() {
        return "Pune";
    }

    private Timestamp anyScheduledTimestamp() {
        return Timestamp.valueOf("2023-02-18 01:24:00.64306");
    }

    private Pageable pagesOfConsultantAvailability() {
        return PageRequest.of(0, 1);
    }
}