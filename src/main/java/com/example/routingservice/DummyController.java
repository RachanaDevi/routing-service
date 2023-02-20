package com.example.routingservice;


import com.example.routingservice.entity.Consultant;
import com.example.routingservice.service.RoutingService;
import model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DummyController {

    @Autowired
    private RoutingService routingService;

    @GetMapping(value = "/checkingJpa")
    @ResponseBody
    public ResponseEntity<Consultant> conAvailability() {
        // see how to fix the timestamp problem
        Consultant consultant = routingService.assignedConsultant(new Ticket(1L,"Washing machine", "2023-02-18 01:24:00"));
       return ResponseEntity.ok(consultant);
    }
}
