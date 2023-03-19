package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private Long ticketId;
    private String concern;
    private String scheduledTimestamp;
    private String place;

    public Ticket() {
    }

    public String scheduledTimestamp() {
        return scheduledTimestamp;
    }

    public String concern() {
        return concern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(concern, ticket.concern) && Objects.equals(scheduledTimestamp, ticket.scheduledTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, concern, scheduledTimestamp);
    }

    public Long ticketId() {
        return ticketId;
    }

    public String place() {
        return place;
    }
}
