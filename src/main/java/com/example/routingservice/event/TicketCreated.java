package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private Long ticketId;
    private String concern;
    private String scheduledTimestamp;
    private String place;

    public TicketCreated() {
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
        TicketCreated ticketCreated = (TicketCreated) o;
        return Objects.equals(ticketId, ticketCreated.ticketId) && Objects.equals(concern, ticketCreated.concern) && Objects.equals(scheduledTimestamp, ticketCreated.scheduledTimestamp);
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
