package com.sparta.auth.application.service;

import com.sparta.auth.application.dto.SignUpRequestDto;
import com.sparta.auth.application.dto.UserResponse;
import com.sparta.auth.application.dto.UserUpdateRequest;
import com.sparta.auth.domain.model.User;
import com.sparta.auth.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(SignUpRequestDto request) {
        String password=passwordEncoder.encode(request.getPassword());
        userRepository.save(User.create(
                request.getUsername(),
                request.getSlack_account(),
                password,
                request.getRole(),
                request.getEmail()
        ));
    }

    @Transactional
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponse::fromEntity);
    }

    @Transactional
    public UserResponse updateUser(UUID userId, UserUpdateRequest request) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException(userId+"는 찾을 수 없는 사용자 아이디입니다."));
        user.update(request.getEmail(),request.getRole());
        userRepository.save(user);
        return new UserResponse(user);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException(userId+"는 찾을 수 없는 사용자 아이디입니다."));
        user.delete(userId);
        userRepository.save(user);
    }

    @Transactional
    public Page<UserResponse> searchUsers(String keyword, PageRequest pageRequest) {
        Page<User> users = userRepository.findByUsernameStartingWithOrEmailStartingWith(keyword, keyword, pageRequest);
        return users.map(UserResponse::fromEntity);
    }

    @PostConstruct
    public UserResponse getUser(UUID user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다."));
        return UserResponse.fromEntity(user);
    }
}
