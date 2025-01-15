package com.enviro.assessment.grad001.sivesandla.controller;

import com.enviro.assessment.grad001.sivesandla.model.Household;
import com.enviro.assessment.grad001.sivesandla.service.UserService;
import com.enviro.assessment.grad001.sivesandla.repository.UserRepository;
import com.enviro.assessment.grad001.sivesandla.household_dto.HouseholdRegistrationRequest;
import com.enviro.assessment.grad001.sivesandla.household_dto.HouseholdAuthRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.grad001.sivesandla.util.CrossLanguageSerializer;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8000") // Allow UI's origin
public class HouseholdController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

	@PostMapping("/api/household/register")
  public ResponseEntity<?> register(@RequestBody HouseholdRegistrationRequest request) {
      // Check if the password and repeatPassword match
      if (!request.getPassword().equals(request.getRepeatPassword())) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("Passwords do not match.");
      }

      // Register the user
      Household registeredHousehold = userService.registerHousehold(
              request.getFirstName(),
              request.getLastName(),
              request.getIdentifier(),
              request.getPassword(),
              request.getAddress()
      );
      return ResponseEntity.status(HttpStatus.CREATED).body(registeredHousehold);
  }

    @PostMapping("/api/household/auth")
    public ResponseEntity<?> householdAuth(@RequestBody HouseholdAuthRequest request) {
        
        if (userService.loginHousehold(request.getUsername(), request.getPassword())) {
            //return "Login successful!";
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.findByUsername(request.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body("Passwords or username do not match.");
    }
}

