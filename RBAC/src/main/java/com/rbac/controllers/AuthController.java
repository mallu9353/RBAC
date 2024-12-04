package com.rbac.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rbac.dto.LoginRequest;
import com.rbac.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private com.rbac.services.AuthService authService;

    @Autowired
    private com.rbac.util.JwtUtil jwtUtil;

    // Register endpoint to create a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(registerRequest);
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Login endpoint to authenticate a user and return a JWT token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    	String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);

    }

    // Endpoint to log out the user (invalidate the token, if necessary)
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Handle token invalidation logic if needed
        return new ResponseEntity<>("User logged out successfully!", HttpStatus.OK);
    }
}

