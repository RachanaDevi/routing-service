package com.example.routingservice.consumer;

import com.example.routingservice.constants.KafkaConfigConstants;
import com.example.routingservice.event.TicketCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TicketConsumer {

    private final Logger logger = LoggerFactory.getLogger(TicketConsumer.class.getName());

    @KafkaListener(topics = KafkaConfigConstants.TICKET_SERVICE_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public void consume(TicketCreated ticketCreated) {
        logger.info("Consuming Message......." + ticketCreated);
    }
}
