package com.sparta.delivery_service.application.dto;

import com.sparta.delivery_service.domain.enums.UserRole;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private UUID userId;
    private String slackAccount;
    private String username;
    private String email;
    private UserRole role;
    private Boolean is_deleted;
}

