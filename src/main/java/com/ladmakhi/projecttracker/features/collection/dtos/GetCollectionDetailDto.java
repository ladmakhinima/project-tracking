package com.ladmakhi.projecttracker.features.collection.dtos;

import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.tasks.Task;

import java.util.List;

public record GetCollectionDetailDto(
        Long id,
        String name,
        Board board,
        List<Task> tasks
) {
}
