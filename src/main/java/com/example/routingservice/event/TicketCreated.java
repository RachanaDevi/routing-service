package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private Ticket ticket;
    private TicketStatus ticketStatus;

    public TicketCreated() {
    }

    public TicketCreated(Ticket ticket, TicketStatus ticketStatus) {
        this.ticket = ticket;
        this.ticketStatus = ticketStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreated that = (TicketCreated) o;
        return Objects.equals(ticket, that.ticket) && ticketStatus == that.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket, ticketStatus);
    }
}
