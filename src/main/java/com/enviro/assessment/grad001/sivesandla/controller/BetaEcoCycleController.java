package com.enviro.assessment.grad001.sivesandla;

import com.enviro.assessment.grad001.sivesandla.model.User;
import com.enviro.assessment.grad001.sivesandla.service.UserService;
import com.enviro.assessment.grad001.sivesandla.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/api")
public class BetaEcoCycleController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(
		    @RequestParam String firstName, 
		    @RequestParam String lastName,
		    @RequestParam String email, 
		    @RequestParam String password, 
		    @RequestParam String passwordRepeat) {
		
		if (!password.equals(passwordRepeat)) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body("Passwords do not match.");
		}

		User registeredUser = userService.registerUser(firstName, lastName, email, password);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
	}

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        if (userService.loginUser(username, password)) {
            //return "Login successful!";
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.findByUsername(username));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body("Passwords or username do not match.");
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
    	return "index";
    }
    
    @GetMapping("/dashboard")
    public String showDashboardPage() {
    	return "dashboard";
    }
    
    @GetMapping("/maps")
    public String showMapsPage() {
    	return "maps";
    }
    
    @GetMapping("/users")
    public String showUsersPage() {
    	return "user";
    }

}

