package com.example.routingservice.repository;


import com.example.routingservice.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    @Query(value = "select c.* from consultants_availability ca inner join consultants c on consultant_id=c.id where :date>=ca.available_from and :date <=ca.available_to and ca.available=true and c.specialization like %:specialization% order by ca.available_from limit 1", nativeQuery = true)
    Optional<Consultant> findNearestAvailableConsultant(Timestamp date, String specialization);

    @Query(value = "select c.* from consultants_availability ca inner join consultants c on consultant_id=c.id where :date>=ca.available_from and :date <=ca.available_to and ca.available=true and c.specialization like %:specialization% and c.place like %:place% order by ca.available_from limit 1", nativeQuery = true)
    Optional<Consultant> findNearestAvailableConsultant(Timestamp date, String specialization, String place);
}
