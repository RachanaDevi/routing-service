package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.entity.Consultant;
import org.springframework.stereotype.Service;

@Service
public class RoutingService {

    private final ConsultantService consultantService;

    public RoutingService(ConsultantService consultantService) {
        this.consultantService = consultantService;
    }

    public Consultant assignedConsultant() {
        return this.consultantService.findNearestAvailableConsultant();
    }
}
