package com.rbac.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rbac.entities.User;
import com.rbac.services.AuthService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthService authService;

    
    @GetMapping("/me")
    public User getCurrentUser(Principal principal) {
        String username = principal.getName();
        Optional<User> user = authService.findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminAccess() {
        return "Hello Admin! You have access to this admin route.";
    }

    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userAccess() {
        return "Hello User! You have access to this user route.";
    }

    
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR')")
    @GetMapping("/common")
    public String commonAccess() {
        return "Hello User or Moderator! You have access to this common route.";
    }
}

