package com.example.routingservice.event;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NotifyConsultant {
    private final Long ticketId;
    private final Long consultantId;

    @JsonCreator
    public NotifyConsultant(Long ticketId, Long consultantId) {
        this.consultantId = consultantId;
        this.ticketId = ticketId;
    }

}
