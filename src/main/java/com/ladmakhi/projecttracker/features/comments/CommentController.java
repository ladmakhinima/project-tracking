package com.ladmakhi.projecttracker.features.comments;

import com.ladmakhi.projecttracker.core.annotations.GetCurrentUser;
import com.ladmakhi.projecttracker.features.comments.dtos.CreateCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.GetCommentDto;
import com.ladmakhi.projecttracker.features.comments.dtos.UpdateCommentDto;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody @Valid CreateCommentDto dto, @GetCurrentUser() User user) throws Exception {
        GetCommentDto responseDto = commentService.createComment(dto, user);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable("id") Long commentId, @GetCurrentUser() User user) throws Exception {
        GetCommentDto responseDto = commentService.deleteCommentById(commentId, user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateContentOfComment(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateCommentDto dto,
            @GetCurrentUser() User user) throws Exception {
        GetCommentDto responseDto = commentService.updateContentOfCommentById(id, user, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
