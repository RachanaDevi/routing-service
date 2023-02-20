package com.example.routingservice.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long consultantId) {
        super(String.format("Could not find customer with id %f", consultantId));
    }
}
