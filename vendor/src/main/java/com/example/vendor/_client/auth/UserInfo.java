package com.example.vendor._client.auth;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private UUID user_id;
    private String username;
    private String email;
    private String role;
    private String slack_account;
    private Boolean is_deleted;
}
