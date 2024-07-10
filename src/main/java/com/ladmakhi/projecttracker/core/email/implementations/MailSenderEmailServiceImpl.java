package com.ladmakhi.projecttracker.core.email.implementations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.core.email.dtos.SendEmailDto;
import org.springframework.stereotype.Service;

@Service
public class MailSenderEmailServiceImpl extends AbstractEmailService {

    @Override
    public boolean sendEmail(SendEmailDto dto) {
        return false;
    }
}
