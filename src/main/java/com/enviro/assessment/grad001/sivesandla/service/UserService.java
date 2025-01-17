package com.enviro.assessment.grad001.sivesandla.service;

import com.enviro.assessment.grad001.sivesandla.model.Household;
import com.enviro.assessment.grad001.sivesandla.model.Collector;
import com.enviro.assessment.grad001.sivesandla.repository.HouseholdRepository;
import com.enviro.assessment.grad001.sivesandla.repository.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private HouseholdRepository householdRepository;
    
    @Autowired
    private CollectorRepository collectorRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    private int householdPoints = 0;
    private int collectorPoints = 0;

    public Household registerHousehold(String firstName, String lastName, String email, String password, String address) {
        String username = generateUsername(email);
        Household household = new Household();
        household.setFirstName(firstName);
        household.setLastName(lastName);
        household.setEmail(email);
        household.setAddress(address);
        household.setPoints(householdPoints);
        household.setPassword(passwordEncoder.encode(password));
        household.setUsername(username);
        return householdRepository.save(household);
    }

    public boolean loginHousehold(String username, String password) {
        Household household = householdRepository.findByUsername(username);
        if (household != null) {
            return passwordEncoder.matches(password, household.getPassword());
        }
        return false;
    }
    
    public Collector registerCollector(String firstName, String lastName, String identifier, String password) {
        Collector collector = new Collector();
        collector.setFirstName(firstName);
        collector.setLastName(lastName);
        collector.setPoints(collectorPoints);

        if (identifier.matches("\\d{10}")) { // Assuming phone number is 10 digits (ZAR cellphone number)
            collector.setPhoneNumber(identifier);
            collector.setEmail(null);
        } else {
            collector.setEmail(identifier);
            collector.setPhoneNumber(null);
        }
        
        if (collector.getPhoneNumber() != null) {
            collector.setUsername(collector.getPhoneNumber());
        } else {
            collector.setUsername(collector.getEmail());
        }

        collector.setPassword(passwordEncoder.encode(password));

        return collectorRepository.save(collector);
    }
  
    public boolean loginCollector(String identifier, String password) {
        Collector collector = null;

        // Check if the identifier is a phone number
        if (identifier.matches("\\d{10}")) {
            collector = collectorRepository.findByPhoneNumber(identifier);
        } else {
            collector = collectorRepository.findByUsername(identifier);
        }

        // Validate collector and password
        if (collector != null) {
            return passwordEncoder.matches(password, collector.getPassword());
        }

        return false;
    }

    /**
     * Extracts the username from an email address by taking the string before the '@' character.
     * @param email the email address
     * @return the username, or null if the email is invalid
     */
    private String generateUsername(String email) {
        if (email == null || !email.contains("@")) {
            return null; // Return null for invalid email input
        }
        return email.substring(0, email.indexOf('@'));
    }
}

