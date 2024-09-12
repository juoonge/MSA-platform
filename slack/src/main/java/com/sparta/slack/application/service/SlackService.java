package com.sparta.slack.application.service;

import com.sparta.slack.application.dto.SlackRequest;
import com.sparta.slack.application.dto.SlackResponse;
import com.sparta.slack.domain.model.Slack;
import com.sparta.slack.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlackService {
    private final SlackRepository slackRepository;

    @Transactional
    public void createSlack(SlackRequest request) {
        Slack slack= Slack.create(request);
        slackRepository.save(slack);
    }

    @Transactional(readOnly = true)
    public SlackResponse getSlackDetail(UUID slackId) {
        Slack slack=slackRepository.findById(slackId)
                .orElseThrow(()->new IllegalArgumentException(slackId+"는 찾을 수 없는 계시물 아이디입니다."));
        return SlackResponse.fromEntity(slack);
    }

    @Transactional(readOnly = true)
    public Page<SlackResponse> getSlacks(Pageable pageable) {
        return slackRepository.findAll(pageable).map(SlackResponse::fromEntity);
    }

    @Transactional
    public void deleteSlack(UUID slackId) {
        Slack slack=slackRepository.findById(slackId)
                .orElseThrow(()->new IllegalArgumentException(slackId+"는 찾을 수 없는 슬랙 아이디입니다."));

        slack.delete(slackId);
        slackRepository.save(slack);
    }
}
