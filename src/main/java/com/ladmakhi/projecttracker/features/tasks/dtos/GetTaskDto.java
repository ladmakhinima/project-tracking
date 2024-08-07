package com.ladmakhi.projecttracker.features.tasks.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetTaskDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate reminderDate;
    private List<String> attachments;
}
