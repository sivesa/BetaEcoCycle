package com.enviro.assessment.grad001.sivesandla.controller;

import com.enviro.assessment.grad001.sivesandla.model.Collector;
import com.enviro.assessment.grad001.sivesandla.service.UserService;
import com.enviro.assessment.grad001.sivesandla.repository.CollectorRepository;
import com.enviro.assessment.grad001.sivesandla.user_dto.CollectorRegistrationRequest;
import com.enviro.assessment.grad001.sivesandla.user_dto.CollectorAuthRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:8000") // Allow UI's origin
public class CollectorController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private CollectorRepository userRepository;

	@PostMapping("/api/collector/register")
  public ResponseEntity<?> register(@RequestBody CollectorRegistrationRequest request) {
      // Check if the password and repeatPassword match
      if (!request.getPassword().equals(request.getRepeatPassword())) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("Passwords do not match.");
      }

      // Register the collector
      Collector registeredCollector = userService.registerCollector(
              request.getFirstName(),
              request.getLastName(),
              request.getIdentifier(),
              request.getPassword()
      );
      return ResponseEntity.status(HttpStatus.CREATED).body(registeredCollector);
  }

    @PostMapping("/api/collector/auth")
    public ResponseEntity<?> login(@RequestBody CollectorAuthRequest loginRequest) {
        boolean isAuthenticated = userService.loginCollector(
                loginRequest.getIdentifier(), 
                loginRequest.getPassword()
        );

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

