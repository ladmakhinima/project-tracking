package com.ladmakhi.projecttracker.features.comments;

import com.ladmakhi.projecttracker.features.comments.dtos.CreateCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.GetCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.UpdateCommentDto;
import com.ladmakhi.projecttracker.features.tasks.Task;
import com.ladmakhi.projecttracker.features.tasks.TaskService;
import com.ladmakhi.projecttracker.features.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public GetCommentDto createComment(CreateCommentDto dto, User creator) throws Exception {
        Task task = taskService.getTaskById(dto.taskId());
        Comment parentComment = null;
        if (dto.parentId() != null) {
            parentComment = findCommentById(dto.parentId());
        }
        Comment comment = new Comment(task, dto.content(), creator, parentComment);
        comment = commentRepository.save(comment);
        return commentMapper.mapCommentToGetCommentDto(comment);
    }

    @Override
    public GetCommentDto deleteCommentById(Long id, User creator) throws Exception {
        Comment comment = findCommentById(id);
        if (!checkIsOwnerPerformAction(comment, creator)) {
            throw new Exception("Only Creator of Comment Can Delete");
        }
        commentRepository.delete(comment);
        return commentMapper.mapCommentToGetCommentDto(comment);
    }

    @Override
    public GetCommentDto updateContentOfCommentById(Long id, User creator, UpdateCommentDto dto) throws Exception {
        Comment comment = findCommentById(id);
        if (!checkIsOwnerPerformAction(comment, creator)) {
            throw new Exception("Only Creator of Comment Can Delete");
        }
        comment.setContent(dto.content());
        comment = commentRepository.save(comment);
        return commentMapper.mapCommentToGetCommentDto(comment);
    }

    @Override
    public Comment findCommentById(Long id) throws Exception {
        return commentRepository.findById(id).orElseThrow(() -> new Exception("Comment is not found"));
    }

    public boolean checkIsOwnerPerformAction(Comment comment, User creator) {
        return comment.getCreator().getId()
                .equals(creator.getId());
    }
}
