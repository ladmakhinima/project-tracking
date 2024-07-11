package com.ladmakhi.projecttracker.features.users;

import com.ladmakhi.projecttracker.features.users.dtos.GetUserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    GetUserResponseDto convertUserToGetUserResponseDto(User user);
}
