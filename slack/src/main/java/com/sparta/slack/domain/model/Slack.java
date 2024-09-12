package com.sparta.slack.domain.model;

import com.sparta.slack.application.dto.SlackRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="p_slacks")
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Builder
public class Slack extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="slack_id")
    private UUID id;

    @Column(nullable=false)
    private String receiver_id;

    @Column(name="slack_message", nullable = false)
    private String message;

    @Column(name="slack_send_at", nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime send_at;

    public static Slack create(SlackRequest request){
        return Slack.builder()
                .receiver_id(request.getReceiver_id())
                .message(request.getMessage())
                .build();
    }
}
