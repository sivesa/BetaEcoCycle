package com.enviro.assessment.grad001.sivesandla.controller;

import com.enviro.assessment.grad001.sivesandla.model.Bin;
import com.enviro.assessment.grad001.sivesandla.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    @Autowired
    private BinService binService;

    @GetMapping
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @GetMapping("/{binId}")
    public ResponseEntity<Bin> getBinById(@PathVariable Long binId) {
        Optional<Bin> bin = binService.getBinById(binId);
        return bin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return binService.saveBin(bin);
    }

    @PutMapping("/{binId}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long binId, @RequestBody Bin updatedBin) {
        return binService.getBinById(binId).map(bin -> {
            bin.setWasteType(updatedBin.getWasteType());
            bin.setCapacity(updatedBin.getCapacity());
            bin.setStatus(updatedBin.getStatus());
            Bin savedBin = binService.saveBin(bin);
            return ResponseEntity.ok(savedBin);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{binId}")
    public ResponseEntity<Void> deleteBin(@PathVariable Long binId) {
        if (binService.getBinById(binId).isPresent()) {
            binService.deleteBin(binId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
