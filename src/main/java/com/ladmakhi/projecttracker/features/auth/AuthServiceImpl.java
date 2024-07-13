package com.ladmakhi.projecttracker.features.auth;

import com.ladmakhi.projecttracker.core.security.JwtService;
import com.ladmakhi.projecttracker.features.auth.dtos.AuthResponseDto;
import com.ladmakhi.projecttracker.features.auth.dtos.UserLoginDto;
import com.ladmakhi.projecttracker.features.users.User;
import com.ladmakhi.projecttracker.features.users.UserService;
import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import com.ladmakhi.projecttracker.features.users.dtos.GetUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto login(UserLoginDto dto) throws Exception {
        GetUserResponseDto user = userService.findUserByEmailAndPassword(dto.getEmail(), dto.getPassword());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, null);
        String token = jwtService.generateToken(authentication);
        AuthResponseDto responseDto = new AuthResponseDto(token);
        return responseDto;
    }

    @Override
    public AuthResponseDto signup(CreateUserDto dto) throws Exception {
        GetUserResponseDto user = userService.createUser(dto);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, null);
        String token = jwtService.generateToken(authentication);
        AuthResponseDto responseDto = new AuthResponseDto(token);
        return responseDto;
    }
}
