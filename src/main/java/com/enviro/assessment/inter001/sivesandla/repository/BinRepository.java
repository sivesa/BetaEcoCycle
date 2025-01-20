package com.enviro.assessment.inter001.sivesandla.repository;

import com.enviro.assessment.inter001.sivesandla.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
}
