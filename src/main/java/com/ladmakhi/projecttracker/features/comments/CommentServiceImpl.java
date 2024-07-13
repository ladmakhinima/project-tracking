package com.ladmakhi.projecttracker.features.comments;

import com.ladmakhi.projecttracker.features.comments.dtos.CreateCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.GetCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.UpdateCommentDto;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.tasks.TaskService;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final TaskService taskService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public GetCommentDto createComment(CreateCommentDto dto, User creator) throws Exception {
        Task task = taskService.getTaskById(dto.getTaskId());

        var comment = Comment.builder()
                .task(task)
                .content(dto.getContent())
                .creator(creator);
        if (dto.getParentId() != null) {
            comment.parent(findCommentById(dto.getParentId()));
        }
        Comment createdComment = commentRepository.save(comment.build());
        return commentMapper.toCommentToGetCommentDto(createdComment);
    }

    @Override
    public GetCommentDto deleteCommentById(Long id, User creator) throws Exception {
        Comment comment = findCommentById(id);
        if (!comment.isPerformActionByOwner(creator)) {
            throw new Exception("Only Creator of Comment Can Delete");
        }
        commentRepository.delete(comment);
        return commentMapper.toCommentToGetCommentDto(comment);
    }

    @Override
    public GetCommentDto updateContentOfCommentById(Long id, User creator, UpdateCommentDto dto) throws Exception {
        Comment comment = findCommentById(id);
        if (!comment.isPerformActionByOwner(creator)) {
            throw new Exception("Only Creator of Comment Can Delete");
        }
        comment.setContent(dto.getContent());
        comment = commentRepository.save(comment);
        return commentMapper.toCommentToGetCommentDto(comment);
    }

    @Override
    public Comment findCommentById(Long id) throws Exception {
        return commentRepository.findById(id)
                .orElseThrow(() -> new Exception("Comment is not found"));
    }
}
