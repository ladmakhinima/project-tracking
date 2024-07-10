package com.ladmakhi.projecttracker.features.comments.dtos;

import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.users.User;

import java.util.List;

public record GetCommentDto(
        Long id,
        String content,
        User creator,
        List<Comment> childrens,
        Task task
) {
}
