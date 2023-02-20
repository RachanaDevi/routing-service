package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoutingService {

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private CustomerService customerService;

    public RoutingService() {
    }

    public RoutingService(ConsultantService consultantService, CustomerService customerService) {
        this.consultantService = consultantService;
        this.customerService = customerService;
    }

    public Consultant assignedConsultant(Ticket ticket) {
        List<Consultant> availableConsultants = consultantService.findAvailableConsultants(ticket.date());// we need to have an effective postgres query
// you have customerId -> from this you will get place
        // ticket created date -> consultant availability date
        // and also a consultant from that place
        return availableConsultants.get(0);
    }
}