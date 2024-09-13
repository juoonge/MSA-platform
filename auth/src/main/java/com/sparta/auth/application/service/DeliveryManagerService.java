package com.sparta.auth.application.service;

import com.sparta.auth.application.dto.DeliveryManagerRequest;
import com.sparta.auth.application.dto.DeliveryManagerResponse;
import com.sparta.auth.application.dto.DeliveryManagerUpdateRequest;
import com.sparta.auth.application.dto.UserUpdateRequest;
import com.sparta.auth.domain.model.DeliveryManager;
import com.sparta.auth.domain.model.User;
import com.sparta.auth.domain.repository.DeliveryManagerRepository;
import com.sparta.auth.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryManagerService {

    private final DeliveryManagerRepository managerRepository;
    private final UserRepository userRepository;

    public void createManager(DeliveryManagerRequest request) {
        User user=userRepository.findById(request.getUser_id())
                .orElseThrow(()->new IllegalArgumentException("해당 아이디의 사용자가 등록되어 있지 않습니다."));

        DeliveryManager manager=DeliveryManager.create(request,user);
        managerRepository.save(manager);
    }


    public Page<DeliveryManagerResponse> getManagers(Pageable pageable) {
        return managerRepository.findAll(pageable).map(DeliveryManagerResponse::fromEntity);
    }

    @Transactional
    public DeliveryManagerResponse updateManager(UUID managerId, DeliveryManagerUpdateRequest request) {
        DeliveryManager manager=managerRepository.findById(managerId)
                .orElseThrow(()->new IllegalArgumentException(managerId+"는 찾을 수 없는 매니저 아이디입니다."));
        manager.update(request.getRole());
        managerRepository.save(manager);
        return DeliveryManagerResponse.fromEntity(manager);
    }

    @Transactional
    public void deleteManager(UUID managerId) {
        DeliveryManager manager=managerRepository.findById(managerId)
                .orElseThrow(()->new IllegalArgumentException(managerId+"는 찾을 수 없는 매니저 아이디입니다."));
        manager.delete(managerId);
        managerRepository.save(manager);
    }

    public Page<DeliveryManagerResponse> searchManagers(String keyword, PageRequest pageRequest) {
        Page<DeliveryManager> managers=managerRepository.findByNameStartingWithOrContactStartingWith(keyword, keyword, pageRequest);
        return managers.map(DeliveryManagerResponse::fromEntity);
    }
}
