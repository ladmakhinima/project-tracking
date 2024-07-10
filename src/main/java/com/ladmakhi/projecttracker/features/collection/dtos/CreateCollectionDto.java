package com.ladmakhi.projecttracker.features.collection.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCollectionDto(
        @NotEmpty(message = "You Must Provide Collection Name")
        @Size(min = 3, message = "Your Collection Name Must Include At Least 3 Character")
        String name,
        @NotNull(message = "You Must Provide Board Id")
        @Min(value = 1, message = "Your Board Id Is Invalid")
        Long boardId
) {
}
