package com.example.routingservice.consumer;

import com.example.routingservice.constants.KafkaConfigConstants;
import com.example.routingservice.model.TicketEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TicketEventConsumer {

    private final Logger logger = LoggerFactory.getLogger(TicketEventConsumer.class.getName());

    @KafkaListener(topics = KafkaConfigConstants.MESSAGE_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public void consume(TicketEvent ticketEvent) {
        logger.info("Consuming Message......." + ticketEvent);
    }
}
