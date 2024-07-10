package com.ladmakhi.projecttracker.configurations;

import com.ladmakhi.projecttracker.core.exceptions.GlobalException;
import com.ladmakhi.projecttracker.core.exceptions.ValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfiguration {
    @Bean
    public GlobalException getGlobalException() {
        return new GlobalException();
    }

    @Bean
    public ValidationException getValidationException() {
        return new ValidationException();
    }
}
