package com.ladmakhi.projecttracker.features.invitations.dtos;

import com.ladmakhi.projecttracker.features.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetInvitationDto {
    private Long id;
    private String token;
    private String email;
    private Board board;
    private Date expireTime;
    private boolean isExpired;
}
