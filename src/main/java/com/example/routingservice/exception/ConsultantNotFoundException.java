package com.example.routingservice.exception;

public class ConsultantNotFoundException extends  RuntimeException{

    public ConsultantNotFoundException(String timestamp, String specialization, String place) {
        super(String.format("Could not find assigned consultant available at {%s} specialized at {%s} and from place {%s}", timestamp, specialization, place));
    }
}
