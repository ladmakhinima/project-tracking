package com.ladmakhi.projecttracker.features.collection.dtos;

import com.ladmakhi.projecttracker.features.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GetCollectionDto {
    private Long id;
    private String name;
    private Board board;
}
