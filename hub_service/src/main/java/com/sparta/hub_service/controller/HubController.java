package com.sparta.hub_service.controller;

import com.sparta.hub_service.application.dto.HubDTO;
import com.sparta.hub_service.application.service.HubService;
import com.sparta.hub_service.common.response.ApiResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hubs")
@RequiredArgsConstructor
public class HubController {

    private final HubService hubService;

    /**
     * 허브 생성
     * @RequestHeader("Authorization") String authorization  createHub 뒤에 삽입하기
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createHub(@RequestBody HubDTO hubDTO) {
        UUID hubId = hubService.createHub(hubDTO);

        ApiResponse response = new ApiResponse(200, "success", "허브 생성", hubId);
        return ResponseEntity.ok(response);
    }

    /**
     * 허브 단건 조회
     * @RequestHeader("Authorization") String authorization
     */
    @GetMapping("/{hubId}")
    public ResponseEntity<ApiResponse> getHub(@PathVariable UUID hubId) {
        HubDTO hubDTO = hubService.getHub(hubId);

        ApiResponse response = new ApiResponse(200, "success", "허브 단건 조회 완료", hubDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * 허브 목록 조회
     * @RequestHeader("Authorization") String authorization
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getHubs() {
        List<HubDTO> hubs = hubService.getAllHubs();

        ApiResponse response = new ApiResponse(200, "success", "허브 목록 조회 완료", hubs);
        return ResponseEntity.ok(response);
    }

    /**
     * 허브 수정
     * @RequestHeader("Authorization") String authorization
     */
    @PatchMapping("/{hubId}")
    public ResponseEntity<ApiResponse> updateHub(
        @PathVariable UUID hubId,
        @RequestBody HubDTO hubDTO
        ) {
        hubService.updateHub(hubId, hubDTO);

        ApiResponse response = new ApiResponse(200, "success", "허브 수정 성공", null);
        return ResponseEntity.ok(response);
    }

    /**
     * 허브 삭제
     */
    @DeleteMapping("/{hubId}")
    public ResponseEntity<ApiResponse> deleteHub(@PathVariable UUID hubId,
        @RequestHeader("Authorization") String authorization) {
        hubService.deleteHub(hubId);

        ApiResponse response = new ApiResponse(200, "success", "허브 삭제 성공", null);
        return ResponseEntity.ok(response);
    }
}

