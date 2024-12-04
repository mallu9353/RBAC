# Spring Boot Role-Based Access Control (RBAC) with JWT

This project demonstrates the implementation of Authentication, Authorization, and Role-Based Access Control (RBAC) using Spring Boot and JWT (JSON Web Tokens). The application allows users to register, authenticate, and access resources based on their assigned roles (e.g., Admin, User).

## Features
**1.User Authentication:**

  Secure login and logout using JWT.
  Passwords are hashed for security.
  
**2.Role-Based Access Control:**
  Assign roles (Admin, User, etc.) to users.
  Control access to resources based on roles and permissions.
  
**3.JWT Integration:**
  Tokens are issued during authentication.
  Tokens are validated for every protected resource request.

**3.Global Exception Handling:**
  Unified handling of errors and exceptions.
  
**4.RESTful API:**
  Clean and modular APIs for user and role management.

##Prerequisites
Java: JDK 17 or higher
Spring Boot: 3.1.x
Maven: For dependency management
Database: MySQL (or any supported database; update configuration as needed)

	
