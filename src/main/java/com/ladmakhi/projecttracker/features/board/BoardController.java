package com.ladmakhi.projecttracker.features.board;

import com.ladmakhi.projecttracker.core.annotations.GetCurrentUser;
import com.ladmakhi.projecttracker.features.board.dtos.CreateOrUpdateBoardDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardDetailResponseDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardResponseDto;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardServiceImpl boardService;

    @PostMapping
    public ResponseEntity<GetBoardResponseDto> createBoardAction(
            @RequestBody @Valid CreateOrUpdateBoardDto dto,
            @GetCurrentUser() User user) throws Exception {
        GetBoardResponseDto board = boardService.createBoard(dto, user);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<GetBoardResponseDto> updateBoardByIdAction(
            @RequestBody @Valid CreateOrUpdateBoardDto dto,
            @PathVariable("id") Long id
    ) throws Exception {
        GetBoardResponseDto updatedBoard = boardService.updateBoardById(id, dto);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GetBoardResponseDto> deleteBoardById(
            @PathVariable("id") Long id,
            @GetCurrentUser User user
    ) throws Exception {
        GetBoardResponseDto deletedBoard = boardService.deleteBoardById(id, user);
        return new ResponseEntity<>(deletedBoard, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<GetBoardDetailResponseDto> getBoardById(
            @PathVariable("id") Long id
    ) throws Exception {
        GetBoardDetailResponseDto board = boardService.getBoardByIdWithDetail(id);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetBoardResponseDto>> getBoards() {
        List<GetBoardResponseDto> boards = boardService.getBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }
}
