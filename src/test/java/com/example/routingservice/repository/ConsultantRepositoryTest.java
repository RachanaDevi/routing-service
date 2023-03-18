package com.example.routingservice.repository;


import com.example.routingservice.fixture.ConsultantFixture;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

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