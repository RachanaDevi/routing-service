package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketAssigned {

    private final UUID ticketId; // you don't need to create and send back the ticket again
    private final Long consultantId;
    private final TicketStatus ticketStatus;


    @JsonCreator
    public TicketAssigned(UUID ticketId, Long consultantId) {
        this.ticketId = ticketId;
        this.consultantId = consultantId;
        this.ticketStatus = TicketStatus.ASSIGNED;
    }

    public static TicketAssigned createdFrom(UUID ticketId, Long consultantId) {
        return new TicketAssigned(ticketId, consultantId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketAssigned that = (TicketAssigned) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(consultantId, that.consultantId) && ticketStatus == that.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, consultantId, ticketStatus);
    }

    public TicketStatus status() {
        return this.ticketStatus;
    }
}
