package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.constants.KafkaConstants;
import com.sysops_squad.routingservice.entity.Consultant;
import com.sysops_squad.routingservice.event.NotifyConsultant;
import com.sysops_squad.routingservice.event.TicketAssigned;
import com.sysops_squad.routingservice.event.TicketCreated;
import com.sysops_squad.routingservice.producer.NotificationPublisher;
import com.sysops_squad.routingservice.producer.TicketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RoutingService {

    private final ConsultantService consultantService;

    private final TicketPublisher ticketAssignmentPublisher;

    private final NotificationPublisher notificationPublisher;

    @Autowired
    public RoutingService(ConsultantService consultantService, TicketPublisher ticketAssignmentPublisher, NotificationPublisher notificationPublisher) {
        this.consultantService = consultantService;
        this.ticketAssignmentPublisher = ticketAssignmentPublisher;
        this.notificationPublisher = notificationPublisher;
    }

    @KafkaListener(topics = KafkaConstants.TICKET_CREATED_TOPIC,
            groupId = KafkaConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public Consultant assignedConsultant(TicketCreated ticketCreated) {
        Consultant assignedConsultant = this.consultantService.findNearestAvailableConsultant(Timestamp.valueOf(ticketCreated.scheduledTimestamp()), ticketCreated.productCategoryId(), ticketCreated.place());
        ticketAssignmentPublisher.publish(TicketAssigned.createdFrom(ticketCreated.ticketId(), assignedConsultant.id()));
        notificationPublisher.publish(new NotifyConsultant(ticketCreated.ticketId(), assignedConsultant.id()));
        return assignedConsultant;
    }
}
