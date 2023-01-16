package com.example.routingservice.repository;

import com.example.routingservice.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    List<Consultant> findBySpecialization(String specialization);

    List<Consultant> findAll();
}
