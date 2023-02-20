package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private String customerId;
    private String concern;
    private String timestamp;

    public Ticket() {
    }

    public Ticket(String customerId, String concern, String timestamp) {
        this.customerId = customerId;
        this.concern = concern;
        this.timestamp = timestamp;
    }

    public String date() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(customerId, ticket.customerId) && Objects.equals(concern, ticket.concern) && Objects.equals(timestamp, ticket.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, concern, timestamp);
    }
}
