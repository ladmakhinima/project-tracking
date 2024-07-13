package com.ladmakhi.projecttracker.features.board;

import com.ladmakhi.projecttracker.features.board.dtos.GetBoardDetailResponseDto;
import com.ladmakhi.projecttracker.features.board.dtos.GetBoardResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    List<GetBoardResponseDto> mapListOfBoardToListOfGetBoardResponseDto(List<Board> dto);

    GetBoardResponseDto mapBoardToGetBoardResponseDto(Board dto);

    @Mapping(source = "team", target = "teams")
    @Mapping(source = "collections", target = "boardCollections")
    GetBoardDetailResponseDto mapBoardToGetBoardDetailResponseDto(Board dto);
}
