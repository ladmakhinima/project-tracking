package com.ladmakhi.projecttracker.features.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);
}
