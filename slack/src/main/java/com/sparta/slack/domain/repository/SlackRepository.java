package com.sparta.slack.domain.repository;

import com.sparta.slack.domain.model.SlackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlackRepository extends JpaRepository<SlackEntity, UUID> {
}
