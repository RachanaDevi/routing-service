package com.example.routingservice.service;

import com.example.routingservice.constants.KafkaConfigConstants;
import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.Customer;
import com.example.routingservice.exception.CustomerNotFoundException;
import com.example.routingservice.event.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;


@Service
public class RoutingService {

    private ConsultantService consultantService;

    private CustomerService customerService;

    public RoutingService() {
    }

    @Autowired
    public RoutingService(ConsultantService consultantService, CustomerService customerService) {
        this.consultantService = consultantService;
        this.customerService = customerService;
    }

    @KafkaListener(topics = KafkaConfigConstants.TICKET_SERVICE_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public Consultant assignedConsultant(Ticket ticketCreated) {
        Optional<Customer> customer = customerService.findById(ticketCreated.customerId());
        customer.orElseThrow(() -> new CustomerNotFoundException(ticketCreated.customerId()));

        Optional<Consultant> nearestAvailableConsultant = consultantService.findNearestAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern(), customer.get().place());
        Optional<Consultant> availableConsultant = consultantService.findAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern());

        Consultant consultant = nearestAvailableConsultant.orElse(availableConsultant.orElse(Consultant.noConsultant()));
        // publish to ticket-service
        // publish to notification-service-topic
        return consultant;
    }
}