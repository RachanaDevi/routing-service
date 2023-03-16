package com.example.routingservice.repository;


import com.example.routingservice.fixture.ConsultantFixture;
import com.example.routingservice.entity.Consultant;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
@Profile("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Disabled
class ConsultantRepositoryTest {

    @Autowired
    private ConsultantRepository consultantRepository;

    @BeforeEach
    void setUp() {
        consultantRepository.save(ConsultantFixture.instance().anyConsultant().withSpecialization(anySpecialization()).build());
    }

    @AfterEach
    void tearDown() {
        consultantRepository.delete(ConsultantFixture.instance().anyConsultant().withSpecialization(anySpecialization()).build());
    }

    @Test
    void shouldReturnNearestAvailableConsultantFromDateAndSpecialization() {
        Page<Consultant> nearestAvailableConsultant = consultantRepository.findNearestAvailableConsultant(anyTimestamp(), anySpecialization(), Pageable.ofSize(1));

        assertThat(nearestAvailableConsultant.get().findFirst().get()).isEqualTo(ConsultantFixture.instance().anyConsultant().withSpecialization(anySpecialization()).build());
    }

    @Test
    void shouldReturnNearestAvailableConsultantFromDateAndSpecializationAndPlace() {
        Page<Consultant> nearestAvailableConsultant = consultantRepository.findNearestAvailableConsultant(anyTimestamp(), anySpecialization(), anyPlace(), Pageable.ofSize(1));

        assertThat(nearestAvailableConsultant.get().findFirst().get()).isEqualTo(ConsultantFixture.instance().anyConsultant().withSpecialization(anySpecialization()).withPlace(anyPlace()).build());
    }

    private String anyPlace() {
        return "Pune";
    }

    private String anySpecialization() {
        return "Washing machine";
    }

    private Timestamp anyTimestamp() {
        return Timestamp.valueOf("2023-02-18 01:24:00");
    }
}