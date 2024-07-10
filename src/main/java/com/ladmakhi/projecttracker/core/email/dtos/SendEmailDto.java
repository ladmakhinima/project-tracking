package com.ladmakhi.projecttracker.core.email.dtos;

public record SendEmailDto(
        String email,
        String subject,
        String description,
        String content
) {
}
