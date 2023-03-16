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
        // this is from customer service, so we have to get data from customer microservice
        // here the invocation is not correct in that case
        Optional<Customer> customer = customerService.findById(ticketCreated.customerId());
        customer.orElseThrow(() -> new CustomerNotFoundException(ticketCreated.customerId()));

        Optional<Consultant> nearestAvailableConsultant = consultantService.findNearestAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern(), customer.get().place());
        Optional<Consultant> availableConsultant = consultantService.findAvailableConsultant(Timestamp.valueOf(ticketCreated.timestamp()), ticketCreated.concern());

        Consultant consultant = nearestAvailableConsultant.orElse(availableConsultant.orElse(Consultant.noConsultant()));
        // all have to be routed

        // we have to update the ticket status in the ticket table

        // so you have to update the availability here
        // but should that be confirmed after the consulant has been confirmed?
        // so let us make sure that the consultant has confirmed it
        // publish to ticket-service
        // to have consultant assigned and remove the consultant availability
        // publish to notification-service-topic
        System.out.println(">>> CONSULTANT"+consultant);
        return consultant;
    }
}