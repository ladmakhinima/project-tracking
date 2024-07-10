package com.ladmakhi.projecttracker.features.tasks.dtos;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.users.User;

import java.time.LocalDate;
import java.util.List;

public record GetTaskDetailDto(
        Long id,
        String title,
        String description,
        LocalDate reminderDate,
        List<String> attachments,
        Collection collection,
        List<User> functors
) {
}
