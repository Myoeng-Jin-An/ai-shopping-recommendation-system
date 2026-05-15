package com.aishop.backend.domain.behavior.controller;

import com.aishop.backend.domain.behavior.dto.requestDTO.BehaviorLogCreateRequest;
import com.aishop.backend.domain.behavior.dto.responseDTO.BehaviorLogResponse;
import com.aishop.backend.domain.behavior.service.BehaviorLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 행동 로그 Controller
 * 행동 로그 API 요청과 응답을 처리하는 역할
 */
@RestController
@RequestMapping("/api/behavior-logs")
@RequiredArgsConstructor
public class BehaviorLogController {

    private final BehaviorLogService behaviorLogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BehaviorLogResponse createBehaviorLog(
            @Valid @RequestBody BehaviorLogCreateRequest request
    ) {
        // 행동 로그 생성 API
        return behaviorLogService.createBehaviorLog(request);
    }

    @GetMapping("/users/{userId}")
    public List<BehaviorLogResponse> getBehaviorLogsByUser(
            @PathVariable Long userId
    ) {
        // 사용자 기준 행동 로그 목록 조회 API
        return behaviorLogService.getBehaviorLogsByUser(userId);
    }

    @GetMapping("/sessions/{sessionId}")
    public List<BehaviorLogResponse> getBehaviorLogsBySession(
            @PathVariable String sessionId
    ) {
        // 세션 기준 행동 로그 목록 조회 API
        return behaviorLogService.getBehaviorLogsBySession(sessionId);
    }
}
