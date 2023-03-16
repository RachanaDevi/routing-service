package com.example.routingservice.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsultantNotFoundExceptionTest {

    @Test
    void shouldReturnExceptionMessage() {
        ConsultantNotFoundException consultantNotFoundException = new ConsultantNotFoundException("anyTimestamp", "anySpecialization", "anyPlace");

        assertThat(consultantNotFoundException.getMessage())
                .isEqualTo("Could not find assigned consultant available at {anyTimestamp} specialized at {anySpecialization} and from place {anyPlace}");
    }
}