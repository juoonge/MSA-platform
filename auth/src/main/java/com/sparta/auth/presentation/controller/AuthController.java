package com.sparta.auth.presentation.controller;

import com.sparta.auth.application.dto.SignUpRequestDto;
import com.sparta.auth.application.dto.UserResponse;
import com.sparta.auth.application.dto.UserUpdateRequest;
import com.sparta.auth.infrastructure.configuration.ApiResponse;
import com.sparta.auth.infrastructure.configuration.SortStandard;
import com.sparta.auth.application.service.AuthService;
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
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequestDto request){
        authService.createUser(request);
        return ResponseEntity.ok(new ApiResponse(200,"success","회원가입 완료",null));
    }

    // 사용자 정보 전체조회
    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue="0") int pageNumber,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="CREATED_DESC")
            SortStandard sort
    ){
        Pageable pageable= PageRequest.of(pageNumber,size,sort.getSort());
        Page<UserResponse> users=authService.getUsers(pageable);
        return ResponseEntity.ok(new ApiResponse(200,"success","사용자 전체조회 성공",users));
    }

    // 사용자 정보 수정
    @PatchMapping("/{user_id}")
    public ResponseEntity<?> updateUser(
            @PathVariable UUID user_id,
            @RequestBody UserUpdateRequest request
    ){
        UserResponse user=authService.updateUser(user_id,request);
        return ResponseEntity.ok(new ApiResponse(200,"success","사용자 정보 수정 완료",user));
    }

    // 사용자 탈퇴
    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID user_id){
        authService.deleteUser(user_id);
        return ResponseEntity.ok(new ApiResponse(200,"success","사용자 탈퇴 성공",null));
    }

    // 사용자 검색
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(
            @RequestParam(defaultValue="0") int pageNumber,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(defaultValue="") String keyword,
            @RequestParam(defaultValue="CREATED_DESC") SortStandard sort){
        size=switch(size){
            case 30->30;
            case 50->50;
            default -> 10;
        };
        Page<UserResponse> users=authService.searchUsers(
                keyword,PageRequest.of(pageNumber,size,sort.getSort())
        );
        return ResponseEntity.ok(new ApiResponse(200,"success","사용자 검색 성공",users));
    }
}
