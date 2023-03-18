package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private Long ticketId;
    private Long customerId;
    private String concern;
    private String timestamp;
    private String place;

    public Ticket() {
    }

    public String timestamp() {
        return timestamp;
    }

    public String concern() {
        return concern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(customerId, ticket.customerId) && Objects.equals(concern, ticket.concern) && Objects.equals(timestamp, ticket.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, customerId, concern, timestamp);
    }

    public Long ticketId() {
        return ticketId;
    }

    public String place() {
        return place;
    }
}
