package com.example.routingservice.event;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NotifyConsultant {
    private final UUID ticketId;
    private final Long consultantId;

    @JsonCreator
    public NotifyConsultant(UUID ticketId, Long consultantId) {
        this.consultantId = consultantId;
        this.ticketId = ticketId;
    }

}
