package com.ladmakhi.projecttracker.features.collection.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateCollectionDto {
    @NotEmpty(message = "You Must Provide Collection Name")
    @Size(min = 3, message = "Your Collection Name Must Include At Least 3 Character")
    private String name;
    @NotNull(message = "You Must Provide Board Id")
    @Min(value = 1, message = "Your Board Id Is Invalid")
    private Long boardId;
}
