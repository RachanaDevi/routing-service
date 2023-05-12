package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.entity.Consultant;
import com.sysops_squad.routingservice.event.TicketCreated;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RoutingService {

    private final ConsultantService consultantService;

    public RoutingService(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    public Consultant assignedConsultant(TicketCreated ticketCreated) {
        return this.consultantService.findNearestAvailableConsultant(Timestamp.valueOf(ticketCreated.scheduledTimestamp()),ticketCreated.productCategoryId(), ticketCreated.place());
    }
}
