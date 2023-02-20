package com.example.routingservice.service;

import com.example.routingservice.constants.KafkaConfigConstants;
import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.Customer;
import com.example.routingservice.event.TicketCreated;
import com.example.routingservice.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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
    public Consultant assignedConsultant(TicketCreated ticketCreated) {
        Optional<Customer> customer = customerService.findById(ticketCreated.ticket().customerId());
        customer.orElseThrow(() -> new CustomerNotFoundException(ticketCreated.ticket().customerId()));

        Optional<Consultant> nearestAvailableConsultant = consultantService.findNearestAvailableConsultant(ticketCreated.ticket().timeStamp(), ticketCreated.ticket().concern(), customer.get().place());
        Optional<Consultant> availableConsultant = consultantService.findAvailableConsultant(ticketCreated.ticket().timeStamp(), ticketCreated.ticket().concern());

        return nearestAvailableConsultant.orElse(availableConsultant.orElse(Consultant.noConsultant()));
    }
}