package com.ladmakhi.projecttracker.features.users.dtos;

import com.ladmakhi.projecttracker.features.users.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String profileUrl;
    private UserStatus status;
    private LocalDate lastStatusUpdateDate;
}
