package com.ladmakhi.projecttracker.features.collection.dtos;

import com.ladmakhi.projecttracker.features.board.Board;

public record GetCollectionDto(
        Long id,
        String name,
        Board board
) {
}
