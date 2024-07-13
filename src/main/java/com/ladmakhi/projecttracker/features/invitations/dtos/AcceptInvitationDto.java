package com.ladmakhi.projecttracker.features.invitations.dtos;

import com.ladmakhi.projecttracker.features.users.dtos.CreateUserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AcceptInvitationDto {
    @NotEmpty(message = "You Must Provide Token")
    private String token;
    @Valid
    private CreateUserDto user;
}
