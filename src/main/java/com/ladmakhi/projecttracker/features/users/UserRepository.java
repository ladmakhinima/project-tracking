package com.ladmakhi.projecttracker.features.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailOrUsername(String email, String username);

    Optional<User> findByEmail(String email);
}
