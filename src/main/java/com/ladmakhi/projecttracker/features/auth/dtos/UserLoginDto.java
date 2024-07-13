package com.ladmakhi.projecttracker.features.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    @NotEmpty(message = "You Must Provide Your Email Address")
    @Email(message = "Your Email Address Contain Invalid Format")
    public String email;
    @NotEmpty(message = "You Must Provide Your Password")
    @Size(min = 8, message = "Your Password Must At Least Contain 8 Character")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
            message = "Your Password Must At Least Contain One Uppercase Letter And One Digits And One Sign")
    public String password;
}
