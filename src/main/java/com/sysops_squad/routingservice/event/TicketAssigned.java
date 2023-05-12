package com.sysops_squad.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketAssigned {

    private final Long ticketId;
    private final Long consultantId;
    @JsonCreator
    public TicketAssigned(Long ticketId, Long consultantId) {
        this.ticketId = ticketId;
        this.consultantId = consultantId;
    }

    public static TicketAssigned createdFrom(Long ticketId, Long consultantId) {
        return new TicketAssigned(ticketId, consultantId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketAssigned that = (TicketAssigned) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(consultantId, that.consultantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, consultantId);
    }
}