package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.entity.Consultant;
import com.sysops_squad.routingservice.entity.ConsultantAvailability;
import com.sysops_squad.routingservice.exception.ConsultantNotFoundException;
import com.sysops_squad.routingservice.exception.ConsultantUnavailableException;
import com.sysops_squad.routingservice.repository.ConsultantAvailabilityRepository;
import com.sysops_squad.routingservice.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ConsultantService {

    private final ConsultantAvailabilityRepository consultantAvailabilityRepository;
    private final ConsultantRepository consultantRepository;

    @Autowired
    public ConsultantService(ConsultantAvailabilityRepository consultantAvailabilityRepository, ConsultantRepository consultantRepository) {
        this.consultantAvailabilityRepository = consultantAvailabilityRepository;
        this.consultantRepository = consultantRepository;
    }

    public Consultant findNearestAvailableConsultant(Timestamp scheduledTimestamp, Long specializationId, String place) {
        Page<ConsultantAvailability> consultantAvailability = this.consultantAvailabilityRepository.findNearestAvailableConsultant(scheduledTimestamp, specializationId, place, Pageable.ofSize(1));
        if (consultantAvailability.get().findFirst().isPresent()) {
            long id = consultantAvailability.get().findFirst().get().id();
            consultantAvailabilityRepository.updateAsUnavailableConsultant(id);
            long consultantId = consultantAvailability.get().findFirst().get().consultantId();
            return consultantRepository.findById(consultantId).orElseThrow(() -> new ConsultantNotFoundException(consultantId));
        }
        throw new ConsultantUnavailableException(String.valueOf(scheduledTimestamp), specializationId, place);
    }
}
