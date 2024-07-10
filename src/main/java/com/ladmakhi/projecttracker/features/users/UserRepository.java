package com.ladmakhi.projecttracker.features.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailOrUsername(String email, String username);

    User findByEmail(String email);
}
