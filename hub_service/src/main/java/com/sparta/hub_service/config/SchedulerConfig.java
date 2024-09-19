package com.sparta.hub_service.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Value("${GEMINI_API_URL}")
    private String geminiApiUrl;

    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 0 8 * * *") // 매일 아침 8시 실행
    public void notifyHubOrderInfo() {
        // 허브별 주문 사항 가져오기
        String hubOrderInfo = getHubOrderInfo();

        // Gemini API를 사용하여 메시지 생성
        String prompt = String.format("허브별 주문 사항: %s", hubOrderInfo);
        String summary = generateContent(prompt);

        // 여기에 슬랙 메시지 전송 코드 추가
        System.out.println("8시 알림 메시지: " + summary);
    }

    private String getHubOrderInfo() {

        

        return "허브별 주문 사항 예시";
    }

    private String generateContent(String prompt) {
        String url = String.format(
            "%s/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s", geminiApiUrl,
            geminiApiKey);

        String requestBody = String.format("{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}", prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("Gemini API 호출 실패");
        }
    }
}
