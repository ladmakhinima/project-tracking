package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.features.invitations.dtos.AcceptInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.CreateInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;

import java.util.Date;
import java.util.List;

public interface InvitationService {
    String generateToken();

    void acceptInvitation(AcceptInvitationDto dto) throws Exception;

    List<GetInvitationDto> getInvitations();

    GetInvitationDto cancelInvitation(Long id) throws Exception;

    GetInvitationDto inviteUserToBoard(CreateInvitationDto dto) throws Exception;

    Date generateExpireTime();
}