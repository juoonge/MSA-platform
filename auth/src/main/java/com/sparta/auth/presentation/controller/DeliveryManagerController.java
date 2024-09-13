package com.sparta.auth.presentation.controller;

import com.sparta.auth.application.dto.*;
import com.sparta.auth.application.service.DeliveryManagerService;
import com.sparta.auth.infrastructure.configuration.ApiResponse;
import com.sparta.auth.infrastructure.configuration.SortStandard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/delivery-manager")
public class DeliveryManagerController {

    private DeliveryManagerService managerService;

    // 배송매니저 생성
    @PostMapping
    public ResponseEntity<?> createManager(@RequestBody DeliveryManagerRequest request){
        managerService.createManager(request);
        return ResponseEntity.ok(new ApiResponse(200,"success","배송매니저 생성 완료",null));
    }

    // 목록 조회
    @GetMapping
    public ResponseEntity<?> getManagers(
            @RequestParam(defaultValue="0") int pageNumber,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="CREATED_DESC")
            SortStandard sort
    ){
        Pageable pageable= PageRequest.of(pageNumber,size,sort.getSort());
        Page<DeliveryManagerResponse> managers=managerService.getManagers(pageable);
        return ResponseEntity.ok(new ApiResponse(200,"success","배송매니저 전체조회 성공",managers));
    }

    // 수정
    @PatchMapping("/{manager_id}")
    public ResponseEntity<?> updateManager(
            @PathVariable UUID manager_id,
            @RequestBody DeliveryManagerUpdateRequest request
    ){
        DeliveryManagerResponse manager=managerService.updateManager(manager_id,request);
        return ResponseEntity.ok(new ApiResponse(200,"success","배송매니저 수정 완료",manager));
    }

    // 삭제
    @DeleteMapping("/{manager_id}")
    public ResponseEntity<?> deleteManager(@PathVariable UUID manager_id){
        managerService.deleteManager(manager_id);
        return ResponseEntity.ok(new ApiResponse(200,"success","배송매니저 삭제 성공",null));
    }

    // 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchManagers(
            @RequestParam(defaultValue="0") int pageNumber,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="") String keyword,
            @RequestParam(defaultValue="CREATED_DESC") SortStandard sort){
        size=switch(size){
            case 30->30;
            case 50->50;
            default -> 10;
        };
        Page<DeliveryManagerResponse> managers=managerService.searchManagers(
                keyword,PageRequest.of(pageNumber,size,sort.getSort())
        );
        return ResponseEntity.ok(new ApiResponse(200,"success","사용자 검색 성공",managers));
    }
}
