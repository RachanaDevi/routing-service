//package com.example.routingservice.producer;
//
//import com.example.routingservice.model.TicketEvent;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CompletableFuture;
//
//import static com.example.routingservice.constants.KafkaConfigConstants.MESSAGE_TOPIC;
//
//@Component
//public class TicketEventPublisher {
//
//    private final KafkaTemplate<String, TicketEvent> kafkaTemplate;
//
//    public TicketEventPublisher(KafkaTemplate<String, TicketEvent> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public CompletableFuture<SendResult<String, TicketEvent>> publish(TicketEvent ticketEvent) {
//        return kafkaTemplate.send(MESSAGE_TOPIC, ticketEvent);
//    }
//}