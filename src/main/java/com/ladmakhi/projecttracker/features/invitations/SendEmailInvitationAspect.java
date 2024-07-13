package com.ladmakhi.projecttracker.features.invitations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.core.email.dtos.SendEmailDto;
import com.ladmakhi.projecttracker.features.invitations.dtos.GetInvitationDto;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SendEmailInvitationAspect {
    @Value("${invitation.accept.link}")
    String acceptLink;

    @Autowired
    @Qualifier("Mail-Sender-Email")
    private AbstractEmailService emailService;

    @AfterReturning(
            value = "execution(* com.ladmakhi.projecttracker.features.invitations.InvitationServiceImpl.inviteUserToBoard(..))",
            returning = "result"
    )
    public void sendEmail(GetInvitationDto result) {
        String email = result.getEmail();
        String subject = String.format("Welcome To Project Tracker, You Have Invitation From %s", result.getBoard().getOwner().getEmail());
        String content = String.format("Click On This Link Or Copy On Your Browser : %s", acceptLink + "?token=" + result.getToken());
        SendEmailDto dto = new SendEmailDto(email, subject, content);
        boolean isSuccess = emailService.sendEmail(dto);
        System.out.println(String.format("Send Email : %s", isSuccess));
    }
}
