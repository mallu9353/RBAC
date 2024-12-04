package com.rbac.util;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    // Constructor injection of PasswordEncoder
    public PasswordUtil() {
        this.passwordEncoder = new BCryptPasswordEncoder();  // You can use any encoder, BCrypt is a secure choice
    }

    /**
     * Hashes the plain password using BCrypt algorithm
     *
     * @param plainPassword The plain password provided by the user
     * @return The hashed password
     */
    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    /**
     * Compares a plain password with the hashed password stored in the database
     *
     * @param plainPassword The plain password provided by the user
     * @param hashedPassword The hashed password stored in the database
     * @return true if the passwords match, false otherwise
     */
    public boolean matches(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}

