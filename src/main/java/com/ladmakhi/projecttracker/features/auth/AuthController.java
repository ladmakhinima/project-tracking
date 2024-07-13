package com.ladmakhi.projecttracker.features.auth;

import com.ladmakhi.projecttracker.features.auth.dtos.AuthResponseDto;
import com.ladmakhi.projecttracker.features.auth.dtos.UserLoginDto;
import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid UserLoginDto dto) throws Exception {
        AuthResponseDto responseDto = authService.login(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody @Valid CreateUserDto dto) throws Exception {
        AuthResponseDto response = authService.signup(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
