package com.ladmakhi.projecttracker.features.board.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardResponseDto {
    private Long id;
    private String name;
}
