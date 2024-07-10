package com.ladmakhi.projecttracker.features.collection.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateCollectionDto(
        @NotEmpty(message = "You Must Provide Collection Name")
        @Size(min = 3, message = "Your Collection Name Must Include At Least 3 Character")
        String name
) {
}
