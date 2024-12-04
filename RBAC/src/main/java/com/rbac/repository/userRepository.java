package com.rbac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rbac.entities.User;
@Repository
public interface userRepository extends JpaRepository<User, Long>  {
	 Optional<User> findByUsername(String username);
}
