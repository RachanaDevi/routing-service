package com.example.routingservice.producer;

import com.example.routingservice.event.TicketAssigned;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.routingservice.constants.KafkaConfigConstants.TICKET_SERVICE_TOPIC;

@Component
public class TicketPublisher {

    private final KafkaTemplate<String, TicketAssigned> kafkaTemplate;

    public TicketPublisher(KafkaTemplate<String, TicketAssigned> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, TicketAssigned>> publish(TicketAssigned ticket) {
        return kafkaTemplate.send(TICKET_SERVICE_TOPIC, ticket);
    }
}