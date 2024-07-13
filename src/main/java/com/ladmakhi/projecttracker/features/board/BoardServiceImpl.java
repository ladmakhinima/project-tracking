package com.ladmakhi.projecttracker.features.board;

import com.ladmakhi.projecttracker.features.board.dtos.CreateOrUpdateBoardDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardResponseDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardDetailResponseDto;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public List<GetBoardResponseDto> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return boardMapper.mapListOfBoardToListOfGetBoardResponseDto(boards);
    }

    @Override
    public GetBoardDetailResponseDto getBoardByIdWithDetail(Long id) throws Exception {
        Board board = getBoardById(id);
        return boardMapper.mapBoardToGetBoardDetailResponseDto(board);
    }

    @Override
    public Board getBoardById(Long id) throws Exception {
        return boardRepository.findById(id)
                .orElseThrow(() -> new Exception("Board Not Found"));
    }

    @Override
    public GetBoardResponseDto createBoard(CreateOrUpdateBoardDto dto, User owner) throws Exception {
        boolean isDuplicateByName = boardRepository.existsByNameAndOwner(dto.getName(), owner);
        if (isDuplicateByName) {
            throw new Exception("Duplicated By Name");
        }
        Board board = Board.builder()
                .name(dto.getName())
                .owner(owner)
                .build();
        Board savedBoard = boardRepository.save(board);
        return boardMapper.mapBoardToGetBoardResponseDto(savedBoard);
    }

    @Override
    public GetBoardResponseDto updateBoardById(Long id, CreateOrUpdateBoardDto dto) throws Exception {
        Board board = getBoardById(id);
        boolean isDuplicatedName = boardRepository.existsByNameAndIdNot(dto.getName(), id);
        if (isDuplicatedName) {
            throw new Exception("Name Is Exist Try Another Name");
        }
        board.setName(dto.getName());
        boardRepository.save(board);
        return boardMapper.mapBoardToGetBoardResponseDto(board);
    }

    @Override
    public GetBoardResponseDto deleteBoardById(Long id, User owner) throws Exception {
        Board board = getBoardById(id);
        if (!board.isPerformActionByOwner(owner)) {
            throw new Exception("Only Owner Can Delete This Board");
        }
        boardRepository.delete(board);
        return boardMapper.mapBoardToGetBoardResponseDto(board);
    }

    @Override
    public void addUserToBoard(Long boardId, User user) throws Exception {
        Board board = getBoardById(boardId);
        if (!board.getTeam().contains(user)) {
            board.getTeam().add(user);
            boardRepository.save(board);
        }
    }

    @Override
    public void deleteUserFromBoard(Long boardId, User user) throws Exception {
        Board board = getBoardById(boardId);
        if (board.getTeam().contains(user)) {
            board.getTeam().remove(user);
            boardRepository.save(board);
        }
    }
}
