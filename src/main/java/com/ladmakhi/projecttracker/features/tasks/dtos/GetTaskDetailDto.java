package com.ladmakhi.projecttracker.features.tasks.dtos;

import com.ladmakhi.projecttracker.features.collection.Collection;
import com.ladmakhi.projecttracker.features.comments.Comment;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GetTaskDetailDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate reminderDate;
    private List<String> attachments;
    private Collection collection;
    private List<User> functors;
    private List<Comment> comments;
    private User creator;
}

