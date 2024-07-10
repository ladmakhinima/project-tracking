package com.ladmakhi.projecttracker.core.email.abstracts;

import com.ladmakhi.projecttracker.core.email.dtos.SendEmailDto;

public abstract class AbstractEmailService {
    public abstract boolean sendEmail(SendEmailDto dto);
}
