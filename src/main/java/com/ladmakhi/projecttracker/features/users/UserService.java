package com.ladmakhi.projecttracker.features.users;

import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import com.ladmakhi.projecttracker.features.users.dtos.GetUserResponseDto;

public interface UserService {
    GetUserResponseDto createUser(CreateUserDto dto) throws Exception;

    GetUserResponseDto findUserByEmailAndPassword(String email, String password) throws Exception;

    GetUserResponseDto changeStatusOfUser(Long id, UserStatus status) throws Exception;

    User findUserById(Long id) throws Exception;
}
