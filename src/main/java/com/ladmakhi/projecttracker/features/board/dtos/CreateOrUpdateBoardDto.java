package com.ladmakhi.projecttracker.features.board.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateOrUpdateBoardDto(
        @NotEmpty(message = "You Must Provide The Board Name")
        @Size(min = 3, message = "Your Board Name Must Include At Least 3 Characters")
        String name
) {
}
