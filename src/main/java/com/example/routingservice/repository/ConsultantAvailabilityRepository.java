package com.example.routingservice.repository;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.ConsultantAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ConsultantAvailabilityRepository extends JpaRepository<Consultant, Long> {

    @Query(value = "select c.* from consultants_availability ca inner join consultants c on ca.consultant_id=c.id where ca.available=true", nativeQuery = true)
//    @Query(value = "From ConsultantAvailability.availableConsultants where :date<=ConsultantAvailability.availableConsultants")
    List<Consultant> findAvailableConsultants(Timestamp date);
}
