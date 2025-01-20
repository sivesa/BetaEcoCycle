package com.enviro.assessment.inter001.sivesandla.controller;

import com.enviro.assessment.inter001.sivesandla.model.Bin;
import com.enviro.assessment.inter001.sivesandla.model.Household;
import com.enviro.assessment.inter001.sivesandla.service.BinService;
import com.enviro.assessment.inter001.sivesandla.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bins")
public class BinController {
    @Autowired
    private BinService binService;
    
    @Autowired
    private UserService householdService;

    // Get all bins for a specific household
    @GetMapping("/household/{householdId}/bins")
    public ResponseEntity<List<Bin>> getBinsForHousehold(@PathVariable Long householdId) {
        Optional<Household> household = householdService.getHouseholdById(householdId);
        if (household.isPresent()) {
            List<Bin> bins = binService.getBinsByHouseholdId(householdId);
            return ResponseEntity.ok(bins);
        }
        return ResponseEntity.notFound().build();
    }

    // Add a bin to a household
    @PostMapping("/household/{householdId}")
    public ResponseEntity<Bin> addBinToHousehold(@PathVariable Long householdId, @RequestBody Bin bin) {
        Optional<Household> household = householdService.getHouseholdById(householdId);
        if (household.isPresent()) {
            bin.setHousehold(household.get());
            Bin savedBin = binService.saveBin(bin);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBin);
        }
        return ResponseEntity.notFound().build();
    }

    // Get a bin by ID (for a specific household)
    @GetMapping("/{binId}")
    public ResponseEntity<Bin> getBinById(@PathVariable Long householdId, @PathVariable Long binId) {
        Optional<Bin> bin = binService.getBinByIdAndHouseholdId(binId, householdId);
        return bin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a bin for a household
    // @PutMapping("/{binId}")
    // public ResponseEntity<Bin> updateBin(
    //     @PathVariable Long householdId, 
    //     @PathVariable Long binId, 
    //     @RequestBody Bin updatedBin) {
    //     Optional<Bin> existingBin = binService.getBinByIdAndHouseholdId(binId, householdId);
    //     if (existingBin.isPresent()) {
    //         Bin bin = existingBin.get();
    //         bin.setWasteType(updatedBin.getWasteType());
    //         bin.setCapacity(updatedBin.getCapacity());
    //         bin.setStatus(updatedBin.getStatus());
    //         Bin savedBin = binService.saveBin(bin);
    //         return ResponseEntity.ok(savedBin);
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    // Delete a bin from a household
    // @DeleteMapping("/{binId}")
    // public ResponseEntity<Void> deleteBin(@PathVariable Long householdId, @PathVariable Long binId) {
    //     Optional<Bin> bin = binService.getBinByIdAndHouseholdId(binId, householdId);
    //     if (bin.isPresent()) {
    //         binService.deleteBin(binId);
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.notFound().build();
    // }
}
