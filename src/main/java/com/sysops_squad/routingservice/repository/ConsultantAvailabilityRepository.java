package com.sysops_squad.routingservice.repository;

import com.sysops_squad.routingservice.entity.ConsultantAvailability;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ConsultantAvailabilityRepository extends JpaRepository<ConsultantAvailability, Long> {

    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca JOIN consultant.consultantsSpecializationsList cs WHERE :scheduledTimestamp>=ca.availableFrom and :scheduledTimestamp <=ca.availableTo and ca.available=true and cs.specializationId = :specializationId and consultant.place like %:place% order by ca.availableFrom")
    Page<ConsultantAvailability> findNearestAvailableConsultant(Timestamp scheduledTimestamp, Long specializationId, String place, Pageable pageable);


    @Modifying(clearAutomatically = true)
    @Query("UPDATE ConsultantAvailability ca SET ca.available = false WHERE ca.id=:id")
    @Transactional
    void updateAsUnavailableConsultant(Long id);
}
