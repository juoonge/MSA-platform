package com.sparta.slack.domain.repository;

import com.sparta.slack.domain.model.Slack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlackRepository extends JpaRepository<Slack, UUID> {
}
