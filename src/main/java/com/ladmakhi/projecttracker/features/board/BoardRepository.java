package com.ladmakhi.projecttracker.features.board;


import com.ladmakhi.projecttracker.features.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByNameAndOwner(String name, User owner);
}

