package com.example.routingservice;


import com.example.routingservice.entity.Consultant;
import com.example.routingservice.event.TicketCreated;
import com.example.routingservice.service.RoutingService;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@Controller
public class DummyController {

    @Autowired
    private RoutingService routingService;

    @GetMapping(value = "/checkingJpa")
    @ResponseBody
    public ResponseEntity<Consultant> conAvailability() {
        Consultant consultant = routingService.assignedConsultant(new TicketCreated(new Ticket(1L, "Washing machine", Timestamp.valueOf("2023-02-18 01:24:00"))));
        return ResponseEntity.ok(consultant);
    }
}
