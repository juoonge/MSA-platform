package com.sparta.hub_service.controller;

import com.sparta.hub_service.application.dto.HubPathDTO;
import com.sparta.hub_service.application.dto.HubPathUpdateResultDTO;
import com.sparta.hub_service.application.service.HubPathService;
import com.sparta.hub_service.common.response.ApiResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub-paths")
public class HubPathController {

    private final HubPathService hubPathService;

    @PostMapping
    public ResponseEntity<ApiResponse> createHubPath(@RequestBody HubPathDTO hubPathDTO) {
        HubPathDTO createdHubPath = hubPathService.createHubPath(hubPathDTO);
        return ResponseEntity.ok(new ApiResponse(201, "created", "허브경로 생성 완료", createdHubPath));
    }


    @GetMapping("/{hubPathId}")
    public ResponseEntity<ApiResponse> getHubPath(@PathVariable UUID hubPathId) {
        HubPathDTO hubPathDTO = hubPathService.getHubPath(hubPathId);
        return ResponseEntity.ok(new ApiResponse(200, "success", "허브 단건경로 조회 성공", hubPathDTO));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHubPaths(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortBy
    ) {

        Pageable pageable = PageRequest.of(page, size,
            sortBy == null ? Sort.by("createdAt").descending() : Sort.by(sortBy));
        Page<HubPathDTO> hubPaths = hubPathService.getAllHubPaths(pageable);

        return ResponseEntity.ok(new ApiResponse(200, "success", "허브 페이징 조회 성공", hubPaths));
    }


    @PutMapping("/{hubPathId}")
    public ResponseEntity<ApiResponse> updateHubPath(
        @PathVariable UUID hubPathId,
        @RequestBody HubPathDTO hubPathDTO) {
        HubPathUpdateResultDTO updatedHubPath = hubPathService.updateHubPath(hubPathId, hubPathDTO);
        return ResponseEntity.ok(new ApiResponse(200, "success", "허브경로 업데이트 및 생성", updatedHubPath));
    }

    @DeleteMapping("/{hubPathId}")
    public ResponseEntity<ApiResponse> deleteHubPath(@PathVariable UUID hubPathId) {
        hubPathService.deleteHubPath(hubPathId);
        return ResponseEntity.ok(new ApiResponse(204, "deleted", "허브경로 삭제", null));
    }
}

