package com.ladmakhi.projecttracker.features.invitations.dtos;

import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record AcceptInvitationDto(
        @NotEmpty(message = "You Must Provide Token")
        String token,
        @Valid
        CreateUserDto user
) {
}
