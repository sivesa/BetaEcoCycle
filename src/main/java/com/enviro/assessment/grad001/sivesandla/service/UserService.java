package com.enviro.assessment.grad001.sivesandla.service;

import com.enviro.assessment.grad001.sivesandla.model.User;
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

    public User registerUser(String firstName, String lastName, String email, String password) {
        String username = generateUsername(email);
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
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

