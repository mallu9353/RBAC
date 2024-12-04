package com.rbac.config;







import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rbac.util.JwtAuthenticationFilter;
import com.rbac.util.JwtUtil;

@Configuration

public class SecurityConfig<CustomUserDetailsService> {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor Injection for dependencies
    public SecurityConfig(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Bean to manage Authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean for Password Encoder (BCrypt is commonly used)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuring Security Filter Chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF as we're using JWT for stateless authentication
            .csrf().disable()

            // Set session management to stateless (since we're using JWT)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // Define which paths should be accessible without authentication
            .and()
            .authorizeRequests()
            .requestMatchers("/auth/login", "/auth/register").permitAll() // Public endpoints
            .requestMatchers("/admin/**").hasRole("ADMIN") // Admin only
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // User and Admin access
            .requestMatchers("/moderator/**").hasRole("MODERATOR") // Moderator access
            .anyRequest().authenticated() // All other requests need to be authenticated

            // Add the JWT filter to the Spring Security filter chain before the UsernamePasswordAuthenticationFilter
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

