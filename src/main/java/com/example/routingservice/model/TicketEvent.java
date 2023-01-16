package com.example.routingservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketEvent {

    private String customerId;
    private String concern;
    private String date;
    private TicketStatus ticketStatus;

    public TicketEvent() {
    }

    public TicketEvent(String customerId, String concern, String date, TicketStatus ticketStatus) {
        this.customerId = customerId;
        this.concern = concern;
        this.date = date;
        this.ticketStatus = ticketStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEvent that = (TicketEvent) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(concern, that.concern) && Objects.equals(date, that.date) && ticketStatus == that.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, concern, date, ticketStatus);
    }


    public TicketStatus status() {
        return this.ticketStatus;
    }

    @Override
    public String toString() {
        return "TicketEvent{" +
                "customerId='" + customerId + '\'' +
                ", concern='" + concern + '\'' +
                ", date='" + date + '\'' +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
