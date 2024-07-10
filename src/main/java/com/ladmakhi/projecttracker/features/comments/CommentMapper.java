package com.ladmakhi.projecttracker.features.comments;

import com.ladmakhi.projecttracker.features.comments.dtos.GetCommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public GetCommentDto mapCommentToGetCommentDto(Comment comment) {
        return new GetCommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreator(),
                comment.getChildren(),
                comment.getTask()
        );
    }
}
