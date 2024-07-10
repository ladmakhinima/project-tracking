package com.ladmakhi.projecttracker.features.comments;

import com.ladmakhi.projecttracker.features.comments.dtos.CreateCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.GetCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.UpdateCommentDto;
import com.ladmakhi.projecttracker.features.users.User;

public interface CommentService {
    GetCommentDto createComment(CreateCommentDto dto, User creator) throws Exception;

    GetCommentDto deleteCommentById(Long id, User creator) throws Exception;

    GetCommentDto updateContentOfCommentById(Long id, User creator, UpdateCommentDto dto) throws Exception;

    Comment findCommentById(Long id) throws Exception;
}
