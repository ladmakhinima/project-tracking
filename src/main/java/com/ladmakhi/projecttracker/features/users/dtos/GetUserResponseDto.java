package com.ladmakhi.projecttracker.features.users.dtos;

import com.ladmakhi.projecttracker.features.users.UserStatus;

import java.time.LocalDate;

public record GetUserResponseDto(
        Long id,
        String username,
        String email,
        String profileUrl,
        UserStatus status,
        LocalDate lastStatusUpdateDate
) {
}
