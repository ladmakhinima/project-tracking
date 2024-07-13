package com.ladmakhi.projecttracker.features.comments;

import com.ladmakhi.projecttracker.features.comments.dtos.GetCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    GetCommentDto toCommentToGetCommentDto(Comment comment);
}
