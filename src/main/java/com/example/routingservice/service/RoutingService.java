package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.Customer;
import com.example.routingservice.exception.CustomerNotFoundException;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Consultant assignedConsultant(Ticket ticket) {
        // see where to handle exceptions and so forth
        Optional<Customer> customer = customerService.findById(ticket.customerId());
        customer.orElseThrow(() -> new CustomerNotFoundException(ticket.customerId()));

        Optional<Consultant> nearestAvailableConsultant = consultantService.findNearestAvailableConsultant(ticket.date(), ticket.concern(), customer.get().place());
        Optional<Consultant> availableConsultant = consultantService.findAvailableConsultant(ticket.date(), ticket.concern());

        return nearestAvailableConsultant.orElse(availableConsultant.orElse(Consultant.noConsultant()));
    }
}