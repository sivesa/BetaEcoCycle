package com.enviro.assessment.inter001.sivesandla.repository;

import com.enviro.assessment.inter001.sivesandla.model.Household;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
    Household findByUsername(String username);
}

