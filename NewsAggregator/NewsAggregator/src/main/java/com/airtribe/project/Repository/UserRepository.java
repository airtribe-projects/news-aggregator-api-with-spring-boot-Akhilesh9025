package com.airtribe.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.project.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
