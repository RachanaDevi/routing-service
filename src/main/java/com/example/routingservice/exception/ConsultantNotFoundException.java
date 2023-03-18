package com.example.routingservice.exception;

public class ConsultantNotFoundException extends  RuntimeException{

    public ConsultantNotFoundException(Long id) {
        super(String.format("Could not find consultant with consultant id {%s}", id));
    }
}
