package com.example.routingservice.repository;

import com.example.routingservice.entity.ConsultantAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ConsultantAvailabilityRepository extends JpaRepository<ConsultantAvailability, Long> {

    @Modifying
    @Query("UPDATE ConsultantAvailability ca SET ca.available = false WHERE ca.consultantId=:consultantId")
    @Transactional
    void updateAsUnavailableConsultant(Long consultantId);
}
