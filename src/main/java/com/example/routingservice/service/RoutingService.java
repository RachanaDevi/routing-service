package com.example.routingservice.service;

import com.example.routingservice.constants.KafkaConfigConstants;
import com.example.routingservice.entity.Consultant;
import com.example.routingservice.event.NotifyConsultant;
import com.example.routingservice.event.Ticket;
import com.example.routingservice.event.TicketAssigned;
import com.example.routingservice.producer.NotificationPublisher;
import com.example.routingservice.producer.TicketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class RoutingService {

    private ConsultantService consultantService;

    private NotificationPublisher notificationPublisher;

    private TicketPublisher ticketAssignmentPublisher;

    public RoutingService() {
    }

    @Autowired
    public RoutingService(ConsultantService consultantService, NotificationPublisher notificationPublisher, TicketPublisher ticketAssignmentPublisher) {
        this.consultantService = consultantService;
        this.notificationPublisher = notificationPublisher;
        this.ticketAssignmentPublisher = ticketAssignmentPublisher;
    }

    @KafkaListener(topics = KafkaConfigConstants.TICKET_CREATED_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public Consultant assignedConsultant(Ticket ticketCreated) {
        Page<Consultant> nearestAvailableConsultant = consultantService.findNearestAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern(), ticketCreated.place());
        Page<Consultant> availableConsultant = consultantService.findAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern());

        // handle if no consultant is there at all
        Consultant consultant = nearestAvailableConsultant.get().findFirst().orElse(availableConsultant.get().findFirst().orElseThrow(() -> new RuntimeException("No consultant found!")));

        consultantService.updateAsUnavailable(consultant);
        notificationPublisher.publish(new NotifyConsultant(ticketCreated.ticketId(), consultant.id()));
        ticketAssignmentPublisher.publish(TicketAssigned.createdFrom(ticketCreated.ticketId(), consultant.id()));

        System.out.println(">>> CONSULTANT" + consultant);
        return consultant;
    }
}