package com.sysops_squad.routingservice.producer;

import com.sysops_squad.routingservice.event.NotifyConsultant;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.sysops_squad.routingservice.constants.KafkaConstants.NOTIFICATION_SERVICE_TOPIC;

@Component
public class NotificationPublisher {

    private final KafkaTemplate<String, NotifyConsultant> kafkaTemplate;

    public NotificationPublisher(KafkaTemplate<String, NotifyConsultant> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, NotifyConsultant>> publish(NotifyConsultant notification) {
        return kafkaTemplate.send(NOTIFICATION_SERVICE_TOPIC, notification);
    }
}
