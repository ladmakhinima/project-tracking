package com.ladmakhi.projecttracker.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String key = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private final SecretKey hashedKey = Keys.hmacShaKeyFor(key.getBytes());

    @Override
    public Claims verifyToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(hashedKey).build().parseClaimsJws(token).getBody();
        String email = String.valueOf(claims.get("email"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return claims;
    }

    @Override
    public String generateToken(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(generateExpireTime())
                .signWith(hashedKey)
                .claim("email", authentication.getName())
                .compact();
    }

    public Date generateExpireTime() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }
}
