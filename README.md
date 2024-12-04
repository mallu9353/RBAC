# Spring Boot Role-Based Access Control (RBAC) with JWT

This project demonstrates the implementation of Authentication, Authorization, and Role-Based Access Control (RBAC) using Spring Boot and JWT (JSON Web Tokens). The application allows users to register, authenticate, and access resources based on their assigned roles (e.g., Admin, User).

## Features
**1.User Authentication:**

 * Secure login and logout using JWT.
 * Passwords are hashed for security.
  
**2.Role-Based Access Control:**

  * Assign roles (Admin, User, etc.) to users.
  * Control access to resources based on roles and permissions.
  
**3.JWT Integration:**

  * Tokens are issued during authentication.
  * Tokens are validated for every protected resource request.

**3.Global Exception Handling:**

  * Unified handling of errors and exceptions.
  
**4.RESTful API:**

  * Clean and modular APIs for user and role management.
  



## Prerequisites
* Java: JDK 17 or higher

* Spring Boot: 3.1.x

* Maven: For dependency management

* Database: MySQL (or any supported database; update configuration as needed)

## Setup Instructions

**1.Clone the repository:**

	git clone https://github.com/mallu9353/RBAC
	cd RBAC

 **2.Configure Database:**

Update src/main/resources/application.properties with your database credentials.

	spring.datasource.url=jdbc:mysql://localhost:3306/rbacdb
	spring.datasource.username=root
	spring.datasource.password=_____

 **3.Run the Application:**

	mvn spring-boot:run

**4.Access the APIs:**

* Base URL: http://localhost:8080

## Technologies Used

* Spring Boot: Framework for building the application.

* JWT: For token-based authentication.

* MySQL: Database for storing users and roles.

* Spring Security: For authentication and authorization.

* Lombok: To reduce boilerplate code.

* Maven: For dependency management.

## Security Implementation

**1.Password Encryption:**

* Passwords are securely hashed using BCrypt.

**2.JWT Token**:

* Tokens are issued during login.

* Tokens contain claims such as username and roles.

**3.Role-Based Access Control:**

* Users are assigned roles that define their access permissions.

**4.Global Exception Handling:**

* Handles and returns meaningful error responses.





	
