package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        Optional<Consultant> availableConsultantHavingSpecialization = consultantService.findAvailableConsultantWithSpecialization(ticket.date(), ticket.concern());
        return availableConsultantHavingSpecialization.orElse(Consultant.noConsultant());
    }
}