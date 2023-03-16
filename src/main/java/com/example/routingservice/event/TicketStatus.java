package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TicketStatus {
    ASSIGNED
}
