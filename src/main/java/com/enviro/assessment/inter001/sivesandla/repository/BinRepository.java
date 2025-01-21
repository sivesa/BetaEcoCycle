package com.enviro.assessment.inter001.sivesandla.repository;

import com.enviro.assessment.inter001.sivesandla.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {  
    Optional<Bin> findByHousehold_HouseholdId(Long householdId);
    
    @Query("SELECT b FROM Bin b WHERE b.household.id = :householdId")
    List<Bin> findByHouseholdId(@Param("householdId") Long householdId);
    
    // @Query("SELECT b FROM Bin b WHERE b.id = :binId AND b.household.id = :householdId")
    // Optional<Bin> findByIdAndHouseholdId(@Param("binId") Long binId, @Param("householdId") Long householdId);
    
    @Query("SELECT b FROM Bin b WHERE b.id = :binId AND b.household.id = :householdId")
    Optional<Bin> findByBinIdAndHouseholdId(@Param("binId") Long binId, @Param("householdId") Long householdId);


    // Optional<Bin> findByIdAndHouseholdId(Long binId, Long householdId);
}
