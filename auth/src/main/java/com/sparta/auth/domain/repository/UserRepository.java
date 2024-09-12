package com.sparta.auth.domain.repository;

import com.sparta.auth.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Page<User> findByNameStartingWithOrContactStartingWith(String keyword, String keyword1, PageRequest pageRequest);
}
