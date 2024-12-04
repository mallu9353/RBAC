package com.rbac.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rbac.dto.RegisterRequest;
import com.rbac.entities.User;
import com.rbac.util.JwtUtil;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private com.rbac.repository.userRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register a new user
 
    public void register(RegisterRequest registerRequest) {
        // Check if the username already exists
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }

        // Create new user and encrypt the password
        User user = new User();
        user.setUsename(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        
        user.setRoles("USER");  // Default role, can be modified based on requirements

        // Save the user to the database
        userRepository.save(user);
    }

	public String authenticate(String username, String password) {
		User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Verify password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate and return the JWT token
        return jwtUtil.generateToken(user);

	}
	 public Optional<User> findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
    
}

