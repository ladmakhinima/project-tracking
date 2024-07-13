package com.ladmakhi.projecttracker.features.tasks.dtos;

import com.ladmakhi.projecttracker.core.annotations.DateValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateTaskDto {
    @NotEmpty(message = "You Must Provide Your Title")
    @Size(min = 3, message = "Your Title Must Include 3 Character")
    private String title;
    @NotEmpty(message = "You Must Provide Your Description")
    @Size(min = 3, message = "Your Description Must Include 3 Character")
    private String description;
    @DateValidation(message = "Your Reminder Date Must Be For Today Or Future", canBeNull = true)
    private String reminderDate;
    @NotNull(message = "You Must Provide The Attachments")
    private List<String> attachments;
}
