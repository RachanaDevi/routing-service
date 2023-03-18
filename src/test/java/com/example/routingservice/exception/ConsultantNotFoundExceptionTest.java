package com.example.routingservice.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsultantNotFoundExceptionTest {

    @Test
    void shouldReturnExceptionMessage() {
        ConsultantNotFoundException consultantNotFoundException = new ConsultantNotFoundException(2L);

        assertThat(consultantNotFoundException.getMessage())
                .isEqualTo("Could not find consultant with consultant id {2}");
    }
}