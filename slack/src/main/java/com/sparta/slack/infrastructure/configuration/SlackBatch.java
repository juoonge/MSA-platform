//package com.sparta.slack.infrastructure.configuration;
//
//import com.sparta.slack.application.dto.SlackRequest;
//import com.sparta.slack.application.service.SlackService;
//import com.sparta.slack.domain.model.SlackEntity;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@RequiredArgsConstructor
//@EnableScheduling
//@Configuration
//public class SlackBatch {
//
//    private final SlackService slackService;
//
//    @Scheduled(cron="0 0/1 * * * *") //1분
//    public void batchTest(){
//        SlackRequest slack= new SlackRequest("1분마다 배치 실행 중","TEST BOT");
//        slackService.createSlack(slack);
//    }
//}