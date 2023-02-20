package com.example.routingservice.repository;

import com.example.routingservice.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    @Query(value = "select c.id, c.name, c.specialization, c.place from consultants_availability inner join consultants c on consultant_id=c.id where current_timestamp>=available_from and available=true")
    List<Consultant> findAvailableConsultant(String date);
}
