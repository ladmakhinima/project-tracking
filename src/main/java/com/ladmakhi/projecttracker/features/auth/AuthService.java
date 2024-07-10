package com.ladmakhi.projecttracker.features.auth;

import com.ladmakhi.projecttracker.features.auth.dtos.AuthResponseDto;
import com.ladmakhi.projecttracker.features.auth.dtos.UserLoginDto;
import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;

public interface AuthService {
    AuthResponseDto login(UserLoginDto dto) throws Exception;

    AuthResponseDto signup(CreateUserDto dto) throws Exception;
}
