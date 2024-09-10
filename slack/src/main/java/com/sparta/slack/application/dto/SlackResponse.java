package com.sparta.slack.application.dto;

import com.sparta.slack.domain.model.Slack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlackResponse {
    private UUID slack_id;
    private String message;
    private UUID receiver_id;
    private LocalDateTime send_at;

    public static SlackResponse fromEntity(Slack slack){
        return new SlackResponse(
                slack.getId(),
                slack.getMessage(),
                slack.getReceiver_id(),
                slack.getSend_at()
        );
    }
}
