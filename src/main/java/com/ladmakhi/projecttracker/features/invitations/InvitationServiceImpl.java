package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.board.BoardService;
import com.ladmakhi.projecttracker.features.invitations.dtos.AcceptInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.CreateInvitationDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;
import com.ladmakhi.projecttracker.features.users.User;
import com.ladmakhi.projecttracker.features.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {
    private final InvitationRepository invitationRepository;
    private final InvitationMapper invitationMapper;
    private final BoardService boardService;
    private final UserService userService;
    @Qualifier("Simple-Email")
    @Autowired
    private AbstractEmailService emailService;

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void acceptInvitation(AcceptInvitationDto dto) throws Exception {
        Invitation invitation = invitationRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new Exception("Invitation Not Found"));

        if (invitation.checkIsExpired()) {
            throw new Exception("Your Invitation Token is expired ...");
        }
        User user = userService.findByEmail(invitation.getEmail());
        if (user == null && dto.getUser() != null) {
            if (!invitation.getEmail().equals(dto.getUser().getEmail())) {
                throw new Exception("Invalid Email For create User");
            }
            userService.createUser(dto.getUser());
            user = userService.findByEmail(dto.getUser().getEmail());
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
        invitationRepository.expireAllTokensByEmail(dto.getEmail());
        String token = generateToken();
        Date expireTime = generateExpireTime();
        Board board = boardService.getBoardById(dto.getBoardId());
        Invitation invitation = Invitation.builder()
                .email(dto.getEmail())
                .token(token)
                .expireTime(expireTime)
                .board(board)
                .build();
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
