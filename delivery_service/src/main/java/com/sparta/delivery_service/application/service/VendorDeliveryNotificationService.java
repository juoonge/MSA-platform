package com.sparta.delivery_service.application.service;

import com.sparta.delivery_service.config.GeminiApiClientConfig;
import com.sparta.delivery_service.domain.entity.Delivery;
import com.sparta.delivery_service.domain.repository.DeliveryRepository;
import com.sparta.delivery_service.infrastructure.UserServiceFeignClient;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j
public class VendorDeliveryNotificationService {

    @Value("${GEMINI_API_URL}")
    private String geminiApiUrl;

    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;

    @Value("${WEATHER_API_URL}")
    private String weatherApiUrl;

    @Value("${WEATHER_API_KEY}")
    private String weatherApiKey;

    private final RestTemplate restTemplate;
    private final GeminiApiClientConfig geminiApiClientConfig;
    private final UserServiceFeignClient userServiceFeignClient;
    private final DeliveryRepository deliveryRepository;

    @Scheduled(cron = "0 0 6 * * *") // 1분마다 실행
    public void notifyWeatherAndDeliveryInfo() {
        // 날씨 정보 가져오기
        String weatherData = getWeatherData();

        // 배송 정보 가져오기
        Map<UUID, String> deliveryInfo = getDeliveryInfo();
        Map<UUID, String> sendDeliveryInfo = new HashMap<>();

        // 각 사용자별로 요약 메시지 생성 및 저장
        for (Map.Entry<UUID, String> entry : deliveryInfo.entrySet()) {
            UUID userId = entry.getKey();
            String deliveryData = entry.getValue();

            // Gemini API를 사용하여 요약 메시지 생성
            String prompt = String.format("날씨 정보: %s\n배송 정보: %s 배송 정보와 날씨 정보를 요약해서 배송원들에게 보낼 수 있도록 해주세요.", weatherData, deliveryData);
            String summary = geminiApiClientConfig.generateContent(prompt);

            // 생성된 요약 메시지를 Map에 저장
            sendDeliveryInfo.put(userId, summary);
        }

        // 결과 출력
        for (Map.Entry<UUID, String> entry : sendDeliveryInfo.entrySet()) {
            UUID userId = entry.getKey();
            String summary = entry.getValue();
            System.out.println("사용자 ID: " + userId + "에게 전송할 메시지: " + summary);
        }
    }

    private Map<UUID, String> getDeliveryInfo() {
        List<Delivery> deliveryList = deliveryRepository.findAll();
        Map<UUID, String> userMap = new HashMap<>();

        for (Delivery delivery : deliveryList) {
            UUID userId = delivery.getUserId();
            userMap.put(userId, userMap.getOrDefault(userId, "") + delivery.getAddress() + " ");
        }

        return userMap;
    }


    private String getWeatherData() {
        System.out.println("weatherApiUrl = " + weatherApiUrl);
        System.out.println("weatherApikey = " + weatherApiKey);

        String encodedApiKey;
        try {
            // URLEncoder로 API 키 퍼센트 인코딩
            encodedApiKey = URLEncoder.encode(weatherApiKey, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("API 키 인코딩 실패", e);
        }

        String url = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
            .queryParam("serviceKey", encodedApiKey)  // 인코딩된 API 키 사용
            .queryParam("pageNo", "1")
            .queryParam("numOfRows", "10")
            .queryParam("dataType", "XML")
            .queryParam("base_date", LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
            .queryParam("base_time", "0500")
            .queryParam("nx", "55")
            .queryParam("ny", "127")
            .build().toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("response.getBody() = " + response.getBody());

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("날씨 정보를 가져오는데 실패했습니다.");
        }
    }


}
