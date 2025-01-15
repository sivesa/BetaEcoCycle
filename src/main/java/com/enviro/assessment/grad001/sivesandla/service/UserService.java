package com.enviro.assessment.grad001.sivesandla.service;

import com.enviro.assessment.grad001.sivesandla.model.Household;
import com.enviro.assessment.grad001.sivesandla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    private int points = 0;

    public Household registerHousehold(String firstName, String lastName, String email, String password, String address) {
        String username = generateUsername(email);
        Household household = new Household();
        household.setFirstName(firstName);
        household.setLastName(lastName);
        household.setEmail(email);
        household.setAddress(address);
        household.setPoints(points);
        household.setPassword(passwordEncoder.encode(password));
        household.setUsername(username);
        return userRepository.save(household);
    }

    public boolean loginHousehold(String username, String password) {
        Household household = userRepository.findByUsername(username);
        if (household != null) {
            return passwordEncoder.matches(password, household.getPassword());
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

