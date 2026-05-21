package com.aishop.backend.domain.anomaly.controller;

import com.aishop.backend.domain.anomaly.dto.requestDTO.AnomalyLogCreateRequest;
import com.aishop.backend.domain.anomaly.dto.responseDTO.AnomalyLogResponse;
import com.aishop.backend.domain.anomaly.service.AnomalyLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 이상 행동 로그 Controller
 * 이상 행동 로그 API 요청과 응답을 처리하는 역할
 */
@RestController
@RequestMapping("/api/anomaly-logs")
@RequiredArgsConstructor
public class AnomalyLogController {

    private final AnomalyLogService anomalyLogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnomalyLogResponse createAnomalyLog(
            @Valid @RequestBody AnomalyLogCreateRequest request
    ) {
        // 이상 행동 로그 생성 API
        return anomalyLogService.createAnomalyLog(request);
    }

    @GetMapping("/users/{userId}")
    public List<AnomalyLogResponse> getAnomalyLogsByUser(
            @PathVariable Long userId
    ) {
        // 사용자 기준 이상 행동 로그 목록 조회 API
        return anomalyLogService.getAnomalyLogsByUser(userId);
    }
}