package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    public Optional<Consultant> findAvailableConsultant(String timeStamp, String specialization) {
        return consultantRepository.findNearestAvailableConsultant(Timestamp.valueOf(timeStamp), specialization);
    }

    public Optional<Consultant> findNearestAvailableConsultant(String timeStamp, String specialization, String place) {
        return consultantRepository.findNearestAvailableConsultant(Timestamp.valueOf(timeStamp), specialization, place);
    }
}
