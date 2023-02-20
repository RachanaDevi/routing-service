package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.ConsultantAvailability;
import com.example.routingservice.entity.Customer;
import com.example.routingservice.repository.ConsultantAvailabilityRepository;
import com.example.routingservice.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantAvailabilityRepository consultantAvailabilityRepository;

    @Autowired
    private ConsultantRepository consultantRepository;


    public List<Consultant> findAvailableConsultants(String date) {
   consultantAvailabilityRepository.findAvailableConsultants(Timestamp.valueOf(date));
//                findAvailableConsultants(Timestamp.valueOf(date));
//        System.out.println(availableConsultants);

        List<Consultant> allAvailableConsultants = new ArrayList<>();
//        availableConsultants.forEach(consultant -> {
//                    Consultant availableConsultant = consultantRepository.findById(consultant.getConsultantId()).orElse(dummyConsultant());
//                    allAvailableConsultants.add(availableConsultant);});
        return allAvailableConsultants;
    }

    private Consultant dummyConsultant() {
        return new Consultant(120L, "dummy consultant", "Washing machine", "Pune");
    }
}
