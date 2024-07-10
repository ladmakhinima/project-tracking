package com.ladmakhi.projecttracker.core.filters;

import com.ladmakhi.projecttracker.core.security.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonWebTokenFilter implements Filter {
    @Autowired
    JwtService jwtService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String headerToken = httpServletRequest.getHeader("Authorization");
        if (headerToken == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = headerToken.split("Bearer ")[1];
        if (token == null) {
            throw new ServletException("UnAuthorized2");
        }
        try {
            jwtService.verifyToken(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception exception) {
            throw new ServletException(exception.getMessage());
        }
    }
}
