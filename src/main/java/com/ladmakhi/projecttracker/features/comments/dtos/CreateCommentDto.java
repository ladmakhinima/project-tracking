package com.ladmakhi.projecttracker.features.comments.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ladmakhi.projecttracker.core.annotations.LongValidation;
import jakarta.validation.constraints.*;

public record CreateCommentDto(
        @NotNull(message = "You Must Provide The Id Of Task")
        @Min(value = 1, message = "You Must Provide The Id Of Task")
        Long taskId,

        @LongValidation(message = "Your Parent Id Must be Integer")
        Long parentId,

        @NotBlank(message = "You Must Provide Content of Comment")
        String content
) {
}
