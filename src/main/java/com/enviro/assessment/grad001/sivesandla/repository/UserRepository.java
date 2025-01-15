package com.enviro.assessment.grad001.sivesandla.repository;

import com.enviro.assessment.grad001.sivesandla.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

public interface UserRepository extends JpaRepository<Household, Long> {
    Household findByUsername(String username);
}

