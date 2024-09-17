package com.sparta.auth.application.dto;

import com.sparta.auth.domain.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {
    private final String email;
    private final UserRole role;
}
