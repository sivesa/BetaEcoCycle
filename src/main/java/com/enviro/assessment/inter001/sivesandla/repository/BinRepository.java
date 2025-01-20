package com.enviro.assessment.inter001.sivesandla.repository;

import com.enviro.assessment.inter001.sivesandla.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {  
    Optional<Bin> findByHousehold_HouseholdId(Long householdId);
    
    // Optional<Bin> findByIdAndHouseholdId(Long binId, Long householdId);
}
