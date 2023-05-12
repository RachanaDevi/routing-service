package com.sysops_squad.routingservice.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsultantUnavailableExceptionTest {

    @Test
    void shouldReturnExceptionMessage() {
        ConsultantUnavailableException consultantNotFoundException = new ConsultantUnavailableException("anyTimestamp", 1L, "anyPlace");

        assertThat(consultantNotFoundException.getMessage())
                .isEqualTo("Could not find assigned consultant available at {anyTimestamp} having specializationId {1} and from place {anyPlace}");
    }
}