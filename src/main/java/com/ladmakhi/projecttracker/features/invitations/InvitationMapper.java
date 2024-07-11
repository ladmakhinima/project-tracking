package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvitationMapper {
    public GetInvitationDto mapInvitationToGetInvitationDto(Invitation dto) {
        return new GetInvitationDto(
                dto.getId(),
                dto.getToken(),
                dto.getEmail(),
                dto.getBoard(),
                dto.getExpireTime(),
                dto.isExpired()
        );
    }

    public List<GetInvitationDto> mapListOfInvitationToListOfGetInvitationDto(List<Invitation> invitations) {
        return invitations.stream().map(invitation -> mapInvitationToGetInvitationDto(invitation)).toList();
    }
}
