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

    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca JOIN consultant.consultantsSpecializationsList cs WHERE cs.specializationId = :specializationId and :date>=ca.availableFrom and :date <=ca.availableTo and ca.available=true order by ca.availableFrom")
//    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca WHERE :date>=ca.availableFrom and :date <=ca.availableTo and ca.available=true and consultant.specializationId = :specializationId order by ca.availableFrom")
    Page<ConsultantAvailability> findAvailableConsultant(Timestamp date, Long specializationId, Pageable pageable);

    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca JOIN consultant.consultantsSpecializationsList cs WHERE :date>=ca.availableFrom and :date <=ca.availableTo and ca.available=true and cs.specializationId = :specializationId and consultant.place like %:place% order by ca.availableFrom")
//    @Query("SELECT ca FROM Consultant consultant JOIN consultant.consultantsAvailabilityList ca WHERE :date>=ca.availableFrom and :date <=ca.availableTo and ca.available=true and consultant.specializationId = :specializationId and consultant.place like %:place% order by ca.availableFrom")
    Page<ConsultantAvailability> findNearestAvailableConsultant(Timestamp date, Long specializationId, String place, Pageable pageable);

    @Modifying
    @Query("UPDATE ConsultantAvailability ca SET ca.available = false WHERE ca.id=:id")
    @Transactional
    void updateAsUnavailableConsultant(Long id);
}
