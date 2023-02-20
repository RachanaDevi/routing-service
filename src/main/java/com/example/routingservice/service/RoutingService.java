package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        // get consultants matching the specialization
        // concern

        List<Consultant> consultantsHavingSpecialization = consultantService.findConsultantsHavingSpecialization(ticket.concern());
        System.out.println(">>> CONSULTANTSS HAVING SPECIALIZATION "+ticket.concern());
        System.out.println(consultantsHavingSpecialization);
        System.out.println(">>> AVAILABLE CONSULTANTSS HAVING SPECIALIZATION "+ticket.date()+ "  "+ticket.concern());
        List<Consultant> availableConsultantsHavingSpecialization = consultantService.findAvailableConsultantsWithSpecialization(ticket.date(), ticket.concern());
        System.out.println(availableConsultantsHavingSpecialization);
        System.out.println(">>> AVAILABLE CONSULTANTSS HAVING SPECIALIZATION ONE "+ticket.date()+ "  "+ticket.concern());
        Optional<Consultant> availableConsultantHavingSpecialization = consultantService.findAvailableConsultantWithSpecialization(ticket.date(), ticket.concern());
        if (availableConsultantHavingSpecialization.isPresent()) {
            return availableConsultantHavingSpecialization.get();
        }
        System.out.println(">>> AVAILABLE CONSULTANTSS !!! "+ticket.date()+ "  "+ticket.concern());

        List<Consultant> availableConsultants = consultantService.findAvailableConsultants(ticket.date()); //according to date
        System.out.println(availableConsultants);
        // finding nearest consultant
        // regex for city

        // then get a consultant for specialization

        return null;
    }
}