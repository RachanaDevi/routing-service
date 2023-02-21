package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketAssigned {

    private final Ticket ticket;
    private final TicketStatus ticketStatus;

    @JsonCreator
    public TicketAssigned(Ticket ticket) {
        this.ticket = ticket;
        this.ticketStatus = TicketStatus.ASSIGNED;
    }

    public static TicketAssigned createdFrom(Ticket ticket) {
        return new TicketAssigned(ticket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketAssigned that = (TicketAssigned) o;
        return Objects.equals(ticket, that.ticket) && ticketStatus == that.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket, ticketStatus);
    }

    public TicketStatus status() {
        return this.ticketStatus;
    }
}
