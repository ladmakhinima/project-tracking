package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.features.invitations.dtos.AcceptInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.CreateInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @PostMapping
    public ResponseEntity<?> addNewUserToBoard(@RequestBody @Valid CreateInvitationDto dto) throws Exception {
        GetInvitationDto responseDto = invitationService.inviteUserToBoard(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> cancelInvitation(@PathVariable("id") Long id) throws Exception {
        GetInvitationDto responseDto = invitationService.cancelInvitation(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getInvitations() {
        List<GetInvitationDto> responseDto = invitationService.getInvitations();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @PostMapping("/accept")
    public ResponseEntity<?> acceptInvitation(@RequestBody @Valid AcceptInvitationDto dto) throws Exception {
        invitationService.acceptInvitation(dto);
        return new ResponseEntity<>("Done Successfully", HttpStatus.OK);
    }
}
