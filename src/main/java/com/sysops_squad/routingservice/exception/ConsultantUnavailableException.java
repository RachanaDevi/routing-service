package com.sysops_squad.routingservice.exception;

public class ConsultantUnavailableException extends RuntimeException {

    public ConsultantUnavailableException() {
        super(String.format("Could not find assigned consultant available"));
    }
}
