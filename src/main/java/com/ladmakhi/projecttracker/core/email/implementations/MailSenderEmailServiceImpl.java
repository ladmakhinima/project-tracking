package com.ladmakhi.projecttracker.core.email.implementations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.core.email.dtos.SendEmailDto;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MailSenderEmailServiceImpl extends AbstractEmailService {

    @Value("${spring.mail.username}")
    private String sendFrom;

    @Value("${mailsender.token}")
    private String token;

    @Override
    public boolean sendEmail(SendEmailDto dto) {
        try {

            Email email = new Email();
            email.setSubject(dto.subject());
            email.setPlain(dto.content());
            email.addRecipient("receiver email", dto.email());
            email.setFrom("sender email", sendFrom);
            MailerSend mailerSend = new MailerSend();
            mailerSend.setToken(token);
            MailerSendResponse response = mailerSend.emails().send(email);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
