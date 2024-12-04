package com.rbac.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rbac.entities.Role;
import com.rbac.entities.User;
import com.rbac.repository.userRepository;

@Service
public class UserService {
	
	@Autowired
	private userRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerUser(String username, String password, Set<Role> roles) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists!");
        }

        User user = new User();
        user.setUsename(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	

}
