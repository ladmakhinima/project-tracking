package com.ladmakhi.projecttracker.features.board.dtos;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetBoardDetailResponseDto {
    private Long id;
    private String name;
    private List<Collection> boardCollections;
    private List<User> teams;
}