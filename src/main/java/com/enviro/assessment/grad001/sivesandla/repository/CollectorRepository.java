package com.enviro.assessment.grad001.sivesandla.repository;


import com.enviro.assessment.grad001.sivesandla.model.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

public interface CollectorRepository extends JpaRepository<Collector, Long> {
    Collector findByUsername(String username);
    Collector findByPhoneNumber(String phoneNumber);
}

