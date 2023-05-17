package com.sysops_squad.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NotifyConsultant {
    private Long ticketId;
    private Long consultantId;

    public NotifyConsultant() {
    }

    public NotifyConsultant(Long ticketId, Long consultantId) {
        this.consultantId = consultantId;
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifyConsultant that = (NotifyConsultant) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(consultantId, that.consultantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, consultantId);
    }
}
