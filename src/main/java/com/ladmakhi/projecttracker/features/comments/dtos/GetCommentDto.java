package com.ladmakhi.projecttracker.features.comments.dtos;

import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCommentDto {
    private Long id;
    private String content;
    private User creator;
    private List<Comment> childrens;
    private Task task;
}
