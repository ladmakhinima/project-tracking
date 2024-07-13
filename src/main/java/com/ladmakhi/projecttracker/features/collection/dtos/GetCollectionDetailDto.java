package com.ladmakhi.projecttracker.features.collection.dtos;

import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.tasks.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetCollectionDetailDto {
    private Long id;
    private String name;
    private Board board;
    private List<Task> tasks;
}
