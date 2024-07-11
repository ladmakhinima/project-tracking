package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.board.BoardService;
import com.ladmakhi.projecttracker.features.invitations.dtos.AcceptInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.CreateInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;
import com.ladmakhi.projecttracker.features.users.User;
import com.ladmakhi.projecttracker.features.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private InvitationMapper invitationMapper;

    @Autowired
    @Qualifier("Simple-Email")
    private AbstractEmailService emailService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void acceptInvitation(AcceptInvitationDto dto) throws Exception {
        Invitation invitation = invitationRepository.findByToken(dto.token())
                .orElseThrow(() ->  new Exception("Invitation Not Found"));

        if (invitation.isExpired() || invitation.getExpireTime().before(new Date())) {
            throw new Exception("Your Invitation Token is expired ...");
        }
        User user = userService.findByEmail(invitation.getEmail());
        if (user == null && dto.user() != null) {
            if (!invitation.getEmail().equals(dto.user().email())) {
                throw new Exception("Invalid Email For create User");
            }
            userService.createUser(dto.user());
            user = userService.findByEmail(dto.user().email());
        }
        if (user == null) {
            throw new Exception("User not found Please Provide User Information");
        }
        invitation.setExpired(true);
        invitationRepository.save(invitation);
        boardService.addUserToBoard(invitation.getBoard().getId(), user);
    }

    @Override
    public List<GetInvitationDto> getInvitations() {
        List<Invitation> invitations = invitationRepository.findAll();
        return invitationMapper.mapListOfInvitationToListOfGetInvitationDto(invitations);
    }

    @Override
    public GetInvitationDto cancelInvitation(Long id) throws Exception {
        Invitation invitation = invitationRepository.findById(id).orElseThrow(() -> new Exception("Invitation not found"));
        invitationRepository.delete(invitation);
        return invitationMapper.mapInvitationToGetInvitationDto(invitation);
    }

    @Override
    public GetInvitationDto inviteUserToBoard(CreateInvitationDto dto) throws Exception {
        invitationRepository.expireAllTokensByEmail(dto.email());
        String token = generateToken();
        Date expireTime = generateExpireTime();
        Board board = boardService.getBoardById(dto.boardId());
        Invitation invitation = new Invitation(dto.email(), token, expireTime, board);
        invitation = invitationRepository.save(invitation);
        return invitationMapper.mapInvitationToGetInvitationDto(invitation);
    }

    @Override
    public Date generateExpireTime() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }
}
