package com.enviro.assessment.inter001.sivesandla.service;

import com.enviro.assessment.inter001.sivesandla.model.Bin;
import com.enviro.assessment.inter001.sivesandla.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinService {

    @Autowired
    private BinRepository binRepository;

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Optional<Bin> getBinById(Long binId) {
        return binRepository.findById(binId);
    }

    public Bin saveBin(Bin bin) {
        return binRepository.save(bin);
    }

    public void deleteBin(Long binId) {
        binRepository.deleteById(binId);
    }
}
