package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    public Page<Consultant> findAvailableConsultant(Timestamp timeStamp, String specialization) {
        return consultantRepository.findNearestAvailableConsultant(timeStamp, specialization, Pageable.ofSize(1));
    }

    public Page<Consultant> findNearestAvailableConsultant(Timestamp timeStamp, String specialization, String place) {
        return consultantRepository.findNearestAvailableConsultant(timeStamp, specialization, place,Pageable.ofSize(1));
    }
}
