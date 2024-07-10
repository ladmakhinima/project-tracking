package com.ladmakhi.projecttracker.features.tasks.dtos;

import java.time.LocalDate;
import java.util.List;

public record GetTaskDto(
        Long id,
        String title,
        String description,
        LocalDate reminderDate,
        List<String> attachments
) {
}
