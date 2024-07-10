package com.ladmakhi.projecttracker.configurations;

import com.ladmakhi.projecttracker.core.email.abstracts.AbstractEmailService;
import com.ladmakhi.projecttracker.core.email.implementations.MailSenderEmailServiceImpl;
import com.ladmakhi.projecttracker.core.email.implementations.SimpleEmailServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {
    @Bean
    @Qualifier("Simple-Email")
    public AbstractEmailService getSimpleEmail() {
        return new SimpleEmailServiceImpl();
    }

    @Bean
    @Qualifier("Mail-Sender-Email")
    public AbstractEmailService getMailSenderEmail() {
        return new MailSenderEmailServiceImpl();
    }
}
