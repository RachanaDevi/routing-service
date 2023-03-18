package com.example.routingservice.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsultantUnavailableExceptionTest {

    @Test
    void shouldReturnExceptionMessage() {
        ConsultantUnavailableException consultantNotFoundException = new ConsultantUnavailableException("anyTimestamp", "anySpecialization", "anyPlace");

        assertThat(consultantNotFoundException.getMessage())
                .isEqualTo("Could not find assigned consultant available at {anyTimestamp} specialized at {anySpecialization} and from place {anyPlace}");
    }
}