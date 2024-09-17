package com.sparta.auth.application.dto;

import com.sparta.auth.domain.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String slack_account;
    private String password;
    private String email;
    private UserRole role;
}
