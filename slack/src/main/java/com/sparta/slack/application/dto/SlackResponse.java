package com.sparta.slack.application.dto;

import com.sparta.slack.domain.model.SlackEntity;
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
    private String receiver_id;
    private LocalDateTime send_at;

    public static SlackResponse fromEntity(SlackEntity slackEntity){
        return new SlackResponse(
                slackEntity.getId(),
                slackEntity.getMessage(),
                slackEntity.getReceiver_id(),
                slackEntity.getSend_at()
        );
    }
}
