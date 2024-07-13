package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvitationMapper {
    GetInvitationDto mapInvitationToGetInvitationDto(Invitation dto);

    List<GetInvitationDto> mapListOfInvitationToListOfGetInvitationDto(List<Invitation> invitations);
}
