package com.ladmakhi.projecttracker.features.invitations;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Invitation SET isExpired=true WHERE email=:email")
    void expireAllTokensByEmail(@Param("email") String email);

    Optional<Invitation> findByToken(String token);
}
