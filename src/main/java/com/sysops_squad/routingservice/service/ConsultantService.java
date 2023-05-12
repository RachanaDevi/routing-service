package com.sysops_squad.routingservice.service;

import com.sysops_squad.routingservice.entity.Consultant;
import com.sysops_squad.routingservice.entity.ConsultantAvailability;
import com.sysops_squad.routingservice.repository.ConsultantAvailabilityRepository;
import com.sysops_squad.routingservice.repository.ConsultantRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService {

    private final ConsultantAvailabilityRepository consultantAvailabilityRepository;
    private final ConsultantRepository consultantRepository;

    public ConsultantService(ConsultantAvailabilityRepository consultantAvailabilityRepository, ConsultantRepository consultantRepository) {
        this.consultantAvailabilityRepository = consultantAvailabilityRepository;
        this.consultantRepository = consultantRepository;
    }

    public Consultant findNearestAvailableConsultant() {
        ConsultantAvailability consultantAvailability = this.consultantAvailabilityRepository.findNearestAvailableConsultant().get();
        return consultantRepository.findById(consultantAvailability.consultantId()).get();
    }
}
