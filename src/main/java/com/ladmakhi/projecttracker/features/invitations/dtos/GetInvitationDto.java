package com.ladmakhi.projecttracker.features.invitations.dtos;

import com.ladmakhi.projecttracker.features.board.Board;

import java.util.Date;

public record GetInvitationDto(
        Long id,
        String token,
        String email,
        Board board,
        Date expireTime,
        boolean isExpired
) {
}
