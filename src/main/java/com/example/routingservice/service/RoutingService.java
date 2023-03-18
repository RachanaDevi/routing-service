package com.example.routingservice.service;

import com.example.routingservice.constants.KafkaConfigConstants;
import com.example.routingservice.entity.Consultant;
import com.example.routingservice.event.NotifyConsultant;
import com.example.routingservice.event.Ticket;
import com.example.routingservice.event.TicketAssigned;
import com.example.routingservice.exception.ConsultantNotFoundException;
import com.example.routingservice.producer.NotificationPublisher;
import com.example.routingservice.producer.TicketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;


@Service
public class RoutingService {

    private final ConsultantService consultantService;

    private final NotificationPublisher notificationPublisher;

    private final TicketPublisher ticketAssignmentPublisher;

    @Autowired
    public RoutingService(ConsultantService consultantService, NotificationPublisher notificationPublisher, TicketPublisher ticketAssignmentPublisher) {
        this.consultantService = consultantService;
        this.notificationPublisher = notificationPublisher;
        this.ticketAssignmentPublisher = ticketAssignmentPublisher;
    }

    @KafkaListener(topics = KafkaConfigConstants.TICKET_CREATED_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public void assignedConsultant(Ticket ticketCreated) {
        Consultant consultant = consultantService.findNearestAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern(), ticketCreated.place());

        notificationPublisher.publish(new NotifyConsultant(ticketCreated.ticketId(), consultant.id()));
        ticketAssignmentPublisher.publish(TicketAssigned.createdFrom(ticketCreated.ticketId(), consultant.id()));

        System.out.println(">>> CONSULTANT" + consultant);
    }
}