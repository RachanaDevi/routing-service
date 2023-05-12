package com.sysops_squad.routingservice.repository;

import com.sysops_squad.routingservice.entity.ConsultantAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultantAvailabilityRepository extends JpaRepository<ConsultantAvailability, Long> {
    Optional<ConsultantAvailability> findNearestAvailableConsultant();
}
