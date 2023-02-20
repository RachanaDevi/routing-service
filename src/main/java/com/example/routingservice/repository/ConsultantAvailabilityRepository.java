package com.example.routingservice.repository;

import com.example.routingservice.entity.Consultant;
import com.example.routingservice.entity.ConsultantAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ConsultantAvailabilityRepository extends JpaRepository<ConsultantAvailability, Long> {

    //    @Query(value = "select c.id, c.name, c.specialization, c.place from consultants_availability inner join consultants c on consultant_id=c.id where :date >=available_from and :date <=available_to and available=true")
    @Query(value = "From ConsultantAvailability.availableConsultants where :date<=ConsultantAvailability.availableConsultants")
    List<ConsultantAvailability> findAvailableConsultants(Timestamp date);
}
