package com.ladmakhi.projecttracker.features.comments.dtos;

import com.ladmakhi.projecttracker.core.annotations.LongValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCommentDto {
    @NotNull(message = "You Must Provide The Id Of Task")
    @Min(value = 1, message = "You Must Provide The Id Of Task")
    private Long taskId;

    @LongValidation(message = "Your Parent Id Must be Integer")
    private Long parentId;

    @NotBlank(message = "You Must Provide Content of Comment")
    private String content;
}
