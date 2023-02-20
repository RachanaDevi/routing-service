package com.example.routingservice.producer;

import com.example.routingservice.event.Ticket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.routingservice.constants.KafkaConfigConstants.TICKET_SERVICE_TOPIC;

@Component
public class TicketEventPublisher {

    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    public TicketEventPublisher(KafkaTemplate<String, Ticket> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, Ticket>> publish(Ticket ticketEvent) {
        return kafkaTemplate.send(TICKET_SERVICE_TOPIC, ticketEvent);
    }
}