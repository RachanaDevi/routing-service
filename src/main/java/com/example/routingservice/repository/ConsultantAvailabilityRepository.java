package com.example.routingservice.repository;

import com.example.routingservice.entity.ConsultantAvailability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface ConsultantAvailabilityRepository extends JpaRepository<ConsultantAvailability, Long> {

    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca WHERE :date>=ca.availableFrom and :date <=ca.availableTo and ca.available=true and consultant.specialization like %:specialization% order by ca.availableFrom")
    Page<ConsultantAvailability> findAvailableConsultant(Timestamp date, String specialization, Pageable pageable);

    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca WHERE :date>=ca.availableFrom and :date <=ca.availableTo and ca.available=true and consultant.specialization like %:specialization% and consultant.place like %:place% order by ca.availableFrom")
    Page<ConsultantAvailability> findNearestAvailableConsultant(Timestamp date, String specialization, String place, Pageable pageable);

    @Modifying
    @Query("UPDATE ConsultantAvailability ca SET ca.available = false WHERE ca.id=:id")
    @Transactional
    void updateAsUnavailableConsultant(Long id);
}
