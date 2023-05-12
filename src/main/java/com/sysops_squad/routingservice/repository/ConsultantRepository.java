package com.sysops_squad.routingservice.repository;

import com.sysops_squad.routingservice.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
}
