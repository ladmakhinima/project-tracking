package com.ladmakhi.projecttracker.features.invitations.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateInvitationDto(
        @NotEmpty(message = "You Must Provide Your Email Address")
        @Email(message = "Your Email Address Contain Invalid Format")
        String email,
        @NotNull(message = "You Must Provide Board Id")
        @Min(value = 1, message = "You Must Provide Board Id")
        Long boardId
) {
}
