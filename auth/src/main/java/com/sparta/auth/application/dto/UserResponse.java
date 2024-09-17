package com.sparta.auth.application.dto;

import com.sparta.auth.domain.model.User;
import com.sparta.auth.domain.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponse {
    private UUID user_id;
    private String username;
    private String email;
    private UserRole role;
    private String slack_account;

    private Boolean is_deleted;

    public static UserResponse fromEntity(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getSlack_account(),
                user.is_deleted()
        );
    }
    public UserResponse(User user){
        this.user_id=user.getId();
        this.username=user.getUsername();
        this.email=user.getEmail();
        this.role=user.getRole();
        this.slack_account=user.getSlack_account();
    }
}




