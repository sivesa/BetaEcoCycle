package com.enviro.assessment.grad001.sivesandla.controller;

import com.enviro.assessment.grad001.sivesandla.model.User;
import com.enviro.assessment.grad001.sivesandla.service.UserService;
import com.enviro.assessment.grad001.sivesandla.repository.UserRepository;
import com.enviro.assessment.grad001.sivesandla.household_dto.HouseholdRegistrationRequest;
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

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@PostMapping("/api/household/register")
  public ResponseEntity<?> register(@RequestBody HouseholdRegistrationRequest request) {
      
      // Check if the password and repeatPassword match
      if (!request.getPassword().equals(request.getRepeatPassword())) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("Passwords do not match.");
      }

      // Register the user
      User registeredUser = userService.registerUser(
              request.getFirstName(),
              request.getLastName(),
              request.getIdentifier(),
              request.getPassword(),
              request.getAddress()
      );
      return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
  }

    @PostMapping("/api/household/auth")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        if (userService.loginUser(username, password)) {
            //return "Login successful!";
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.findByUsername(username));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body("Passwords or username do not match.");
    }
}

