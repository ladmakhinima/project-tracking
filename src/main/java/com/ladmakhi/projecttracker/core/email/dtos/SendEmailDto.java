package com.ladmakhi.projecttracker.core.email.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailDto {
    public String email;
    public String subject;
    public String content;
}
