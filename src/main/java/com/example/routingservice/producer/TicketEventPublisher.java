package com.example.routingservice.producer;

import com.example.routingservice.event.TicketCreated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.routingservice.constants.KafkaConfigConstants.TICKET_SERVICE_TOPIC;

@Component
public class TicketEventPublisher {

    private final KafkaTemplate<String, TicketCreated> kafkaTemplate;

    public TicketEventPublisher(KafkaTemplate<String, TicketCreated> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, TicketCreated>> publish(TicketCreated ticketEvent) {
        return kafkaTemplate.send(TICKET_SERVICE_TOPIC, ticketEvent);
    }
}