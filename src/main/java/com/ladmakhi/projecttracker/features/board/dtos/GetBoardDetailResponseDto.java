package com.ladmakhi.projecttracker.features.board.dtos;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.users.User;

import java.util.List;

public record GetBoardDetailResponseDto(
        Long id,
        String name,
        List<Collection> boardCollections,
        List<User> teams
) {
}
