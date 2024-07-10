package com.ladmakhi.projecttracker.features.board;

import com.ladmakhi.projecttracker.features.board.dtos.CreateOrUpdateBoardDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardResponseDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardDetailResponseDto;
import com.ladmakhi.projecttracker.features.users.User;

import java.util.List;

public interface BoardService {
    List<GetBoardResponseDto> getBoards();

    GetBoardDetailResponseDto getBoardByIdWithDetail(Long id) throws Exception;

    Board getBoardById(Long id) throws Exception;

    GetBoardResponseDto createBoard(CreateOrUpdateBoardDto dto, User owner) throws Exception;

    GetBoardResponseDto updateBoardById(Long id, CreateOrUpdateBoardDto dto) throws Exception;

    GetBoardResponseDto deleteBoardById(Long id, User owner) throws Exception;
}
