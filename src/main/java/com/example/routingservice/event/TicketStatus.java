package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TicketStatus {
    ASSIGNED
}
