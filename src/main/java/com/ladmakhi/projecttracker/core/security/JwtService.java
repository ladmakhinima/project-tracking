package com.ladmakhi.projecttracker.core.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {
    Claims verifyToken(String token);

    String generateToken(Authentication authentication);
}
