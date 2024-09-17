package com.sparta.slack.presentation.controller;

import com.sparta.slack.application.dto.SlackRequest;
import com.sparta.slack.application.dto.SlackResponse;
import com.sparta.slack.application.service.SlackService;
import com.sparta.slack.infrastructure.configuration.ApiResponse;
import com.sparta.slack.infrastructure.configuration.SortStandard;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/slacks")
@RequiredArgsConstructor
public class SlackController {
    private final SlackService slackService;

    // 슬랙 생성
    @PostMapping
    @PreAuthorize("hasRole('MASTER')") // ROLE_MASTER
    public ResponseEntity<?> createSlack(
            @RequestBody SlackRequest request){
        slackService.createSlack(request);
        return ResponseEntity.ok(new ApiResponse(200,"success","슬랙 전송 성공",null));
    }

    // 슬랙 메세지 단건 조회
    @GetMapping("/{slack_id}")
    public ResponseEntity<?> getSlackDetail(@PathVariable UUID slack_id){
        SlackResponse slack=slackService.getSlackDetail(slack_id);
        return ResponseEntity.ok(new ApiResponse(200,"success","슬랙 조회 완료",slack));
    }

    // 슬랙 목록 조회
    @GetMapping
    public ResponseEntity<?> getSlacks(
            @RequestParam(defaultValue="0") int pageNumber,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="CREATED_DESC")
            SortStandard sort
    ){
        Pageable pageable= PageRequest.of(pageNumber,size,sort.getSort());
        Page<SlackResponse> slacks=slackService.getSlacks(pageable);
        return ResponseEntity.ok(new ApiResponse(200,"success","슬랙 조회 성공",slacks));
    }

    // 슬랙 메세지 삭제
    @DeleteMapping("/{slack_id}")
    public ResponseEntity<?> deleteSlack(@PathVariable UUID slack_id){
        slackService.deleteSlack(slack_id);
        return ResponseEntity.ok(new ApiResponse(200,"success","슬랙 메세지 삭제 성공",null));
    }
}
