package com.sparta.slack.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlackRequest {
    private String message;
    private String receiver_id;
}
