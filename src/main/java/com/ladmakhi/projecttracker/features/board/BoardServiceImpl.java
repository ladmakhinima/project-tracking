package com.ladmakhi.projecttracker.features.board;

import com.ladmakhi.projecttracker.features.board.dtos.CreateOrUpdateBoardDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardResponseDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardDetailResponseDto;
import com.ladmakhi.projecttracker.features.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<GetBoardResponseDto> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards
                .stream()
                .map(e -> {
                    GetBoardResponseDto dto = new GetBoardResponseDto(e.getId(), e.getName());
                    return dto;
                })
                .toList();
    }

    @Override
    public GetBoardDetailResponseDto getBoardByIdWithDetail(Long id) throws Exception {
        Board board = getBoardById(id);
        GetBoardDetailResponseDto dto = new GetBoardDetailResponseDto(board.getId(), board.getName(), board.getCollections());
        return dto;
    }

    @Override
    public Board getBoardById(Long id) throws Exception {
        return boardRepository.findById(id).orElseThrow(() -> new Exception("Board Not Found"));
    }

    @Override
    public GetBoardResponseDto createBoard(CreateOrUpdateBoardDto dto, User owner) throws Exception {
        boolean isDuplicateByName = boardRepository.existsByNameAndOwner(dto.name(), owner);
        if (isDuplicateByName) {
            throw new Exception("Duplicated By Name");
        }
        Board board = new Board();
        board.setName(dto.name());
        board.setOwner(owner);
        Board savedBoard = boardRepository.save(board);
        GetBoardResponseDto responseDto = new GetBoardResponseDto(savedBoard.getId(), savedBoard.getName());
        return responseDto;
    }

    @Override
    public GetBoardResponseDto updateBoardById(Long id, CreateOrUpdateBoardDto dto) throws Exception {
        Board board = getBoardById(id);
        boolean isDuplicatedName = boardRepository.existsByNameAndIdNot(dto.name(), id);
        if (isDuplicatedName) {
            throw new Exception("Name Is Exist Try Another Name");
        }
        board.setName(dto.name());
        boardRepository.save(board);
        GetBoardResponseDto responseDto = new GetBoardResponseDto(board.getId(), board.getName());
        return responseDto;
    }

    @Override
    public GetBoardResponseDto deleteBoardById(Long id, User owner) throws Exception {
        Board board = getBoardById(id);
        if (!board.getOwner().getId().equals(owner.getId())) {
            throw new Exception("Only Owner Can Delete This Board");
        }
        boardRepository.delete(board);
        GetBoardResponseDto dto = new GetBoardResponseDto(board.getId(), board.getName());
        return dto;
    }
}
