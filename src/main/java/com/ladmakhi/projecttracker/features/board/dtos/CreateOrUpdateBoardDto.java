package com.ladmakhi.projecttracker.features.board.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrUpdateBoardDto {
    @NotEmpty(message = "You Must Provide The Board Name")
    @Size(min = 3, message = "Your Board Name Must Include At Least 3 Characters")
    private String name;
}

