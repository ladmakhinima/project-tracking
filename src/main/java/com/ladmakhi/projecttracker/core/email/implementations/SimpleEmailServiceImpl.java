package com.ladmakhi.projecttracker.core.email.implementations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.core.email.dtos.SendEmailDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class SimpleEmailServiceImpl extends AbstractEmailService {
    @Value("${spring.mail.username}")
    private String sendFrom;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public boolean sendEmail(SendEmailDto dto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(dto.getSubject());
            message.setText(dto.getContent());
            message.setTo(dto.getEmail());
            message.setFrom(sendFrom);
            emailSender.send(message);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
