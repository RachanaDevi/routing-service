package com.example.routingservice.service;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.ConsultantAvailability;
import com.example.routingservice.exception.ConsultantNotFoundException;
import com.example.routingservice.exception.ConsultantUnavailableException;
import com.example.routingservice.repository.ConsultantAvailabilityRepository;
import com.example.routingservice.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ConsultantService {

    private final ConsultantRepository consultantRepository;

    private final ConsultantAvailabilityRepository consultantAvailabilityRepository;

    @Autowired
    public ConsultantService(ConsultantRepository consultantRepository, ConsultantAvailabilityRepository consultantAvailabilityRepository) {
        this.consultantRepository = consultantRepository;
        this.consultantAvailabilityRepository = consultantAvailabilityRepository;
    }

    public Consultant findNearestAvailableConsultant(Timestamp timeStamp, String specialization, String place) {
        Page<ConsultantAvailability> nearestAvailableConsultant = consultantAvailabilityRepository.findNearestAvailableConsultant(timeStamp, specialization, place, Pageable.ofSize(1));
        if (nearestAvailableConsultant.get().findFirst().isPresent()) {
            long id = nearestAvailableConsultant.get().findFirst().get().id();
            consultantAvailabilityRepository.updateAsUnavailableConsultant(id);
            Optional<Consultant> availableConsultant = consultantRepository.findById(nearestAvailableConsultant.get().findFirst().get().consultantId());
            if (availableConsultant.isPresent()) {
                return availableConsultant.get();
            }
        }
        return findAvailableConsultant(timeStamp, specialization, place);
    }

    private Consultant findAvailableConsultant(Timestamp timeStamp, String specialization, String place) {
        Page<ConsultantAvailability> availableConsultant = consultantAvailabilityRepository.findAvailableConsultant(timeStamp, specialization, Pageable.ofSize(1));
        if (availableConsultant.get().findFirst().isPresent()) {
            consultantAvailabilityRepository.updateAsUnavailableConsultant(availableConsultant.get().findFirst().get().id());
            long id = availableConsultant.get()
                    .findFirst()
                    .get().consultantId();

            return consultantRepository.findById(id)
                    .orElseThrow(() -> new ConsultantNotFoundException(id));
        }
        throw new ConsultantUnavailableException(String.valueOf(timeStamp), specialization, place);
    }
}
