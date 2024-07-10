package com.ladmakhi.projecttracker.features.collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    boolean existsByNameAndBoard_Id(String name, Long boardId);
}
