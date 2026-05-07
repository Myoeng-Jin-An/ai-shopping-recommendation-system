package com.aishop.backend.domain.user.controller;

import com.aishop.backend.domain.user.dto.requestDTO.UserCreateRequest;
import com.aishop.backend.domain.user.dto.responseDTO.UserResponse;
import com.aishop.backend.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User API 요청/응답 처리 역할
 * 회원 생성, 회원 조회 요청을 Service 계층으로 전달하고
 * 처리 결과를 HTTP 응답으로 반환
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 신규 사용자 생성 API
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 사용자 단건 조회 API
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse response = userService.getUser(userId);

        return ResponseEntity.ok(response);
    }
}
