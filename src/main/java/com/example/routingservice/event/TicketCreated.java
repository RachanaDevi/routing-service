package com.example.routingservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private Long ticketId;

    private Long productId;

    private Long productCategoryId;
    private String concern;
    private String scheduledTimestamp;
    private String place;

    public TicketCreated() {
    }

    public String scheduledTimestamp() {
        return scheduledTimestamp;
    }

    public Long productCategoryId() {
        return productCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreated that = (TicketCreated) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(productId, that.productId) && Objects.equals(productCategoryId, that.productCategoryId) && Objects.equals(concern, that.concern) && Objects.equals(scheduledTimestamp, that.scheduledTimestamp) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, productId, productCategoryId, concern, scheduledTimestamp, place);
    }

    public Long ticketId() {
        return ticketId;
    }

    public String place() {
        return place;
    }
}
