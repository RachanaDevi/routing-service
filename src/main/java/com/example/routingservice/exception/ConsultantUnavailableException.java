package com.example.routingservice.exception;

public class ConsultantUnavailableException extends RuntimeException {

    public ConsultantUnavailableException(String timestamp, Long specializationId, String place) {
        super(String.format("Could not find assigned consultant available at {%s} having specializationId {%s} and from place {%s}", timestamp, specializationId, place));
    }
}
