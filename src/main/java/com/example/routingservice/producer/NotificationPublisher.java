package com.example.routingservice.producer;

import com.example.routingservice.event.NotifyConsultant;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.routingservice.constants.KafkaConfigConstants.NOTIFICATION_SERVICE_TOPIC;
import static com.example.routingservice.constants.KafkaConfigConstants.TICKET_ASSIGNED_TOPIC;

@Component
public class NotificationPublisher {

    private final KafkaTemplate<String, NotifyConsultant> kafkaTemplate;

    public NotificationPublisher(KafkaTemplate<String, NotifyConsultant> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String,  NotifyConsultant>> publish(NotifyConsultant notification) {
        return kafkaTemplate.send(NOTIFICATION_SERVICE_TOPIC, notification);
    }
}