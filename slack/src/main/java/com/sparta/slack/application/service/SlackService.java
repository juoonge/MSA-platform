package com.sparta.slack.application.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.sparta.slack.application.dto.SlackRequest;
import com.sparta.slack.application.dto.SlackResponse;
import com.sparta.slack.domain.hub.HubResponseDto;
import com.sparta.slack.domain.hub.HubService;
import com.sparta.slack.domain.model.SlackEntity;
import com.sparta.slack.domain.repository.SlackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlackService {
    private final SlackRepository slackRepository;
    private final HubService hubService;

    @Value(value="${slack.token}")
    String slackToken;

    @Transactional
    public void createSlack(SlackRequest request) {
        SlackEntity slackEntity = SlackEntity.create(request);
        slackRepository.save(slackEntity);

        String channel="TEST";

        // 챗봇 메세지 생성
        String message=
                "내용: "+request.getMessage()+"\n"+
                "수신자: "+request.getReceiver_id()+"\n";

        try{
            MethodsClient methods= Slack.getInstance().methods(slackToken);

            ChatPostMessageRequest sendMessage=ChatPostMessageRequest.builder()
                    .channel(channel)
                    .text(message)
                    .build();
            methods.chatPostMessage(sendMessage);

            log.info("Slack " + channel + " 에 메시지 보냄");
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public SlackResponse getSlackDetail(UUID slackId) {
        SlackEntity slackEntity =slackRepository.findById(slackId)
                .orElseThrow(()->new IllegalArgumentException(slackId+"는 찾을 수 없는 계시물 아이디입니다."));
        return SlackResponse.fromEntity(slackEntity);
    }

    @Transactional(readOnly = true)
    public Page<SlackResponse> getSlacks(Pageable pageable) {
        return slackRepository.findAll(pageable).map(SlackResponse::fromEntity);
    }

    @Transactional
    public void deleteSlack(UUID slackId) {
        SlackEntity slackEntity =slackRepository.findById(slackId)
                .orElseThrow(()->new IllegalArgumentException(slackId+"는 찾을 수 없는 슬랙 아이디입니다."));

        //slackEntity.delete(slackId);
        slackRepository.save(slackEntity);
    }
}
