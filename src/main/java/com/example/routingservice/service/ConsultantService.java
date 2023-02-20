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

    public Optional<Consultant> findAvailableConsultantWithSpecialization(String timeStamp, String specialization) {
        return consultantRepository.findAvailableConsultantWithSpecialization(Timestamp.valueOf(timeStamp), specialization);
    }

    public Optional<Consultant> findNearestAvailableConsultantWithSpecialization(String timeStamp, String specialization, String place) {
        return consultantRepository.findNearestAvailableConsultantWithSpecialization(Timestamp.valueOf(timeStamp), specialization, place);
    }
}
