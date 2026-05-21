package com.aishop.backend.domain.recommendation.controller;

import com.aishop.backend.domain.recommendation.dto.requestDTO.RecommendationCreateRequest;
import com.aishop.backend.domain.recommendation.dto.responseDTO.RecommendationResponse;
import com.aishop.backend.domain.recommendation.service.RecommendationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 추천 결과 Controller
 * 추천 결과 API 요청과 응답을 처리하는 역할
 */
@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecommendationResponse createRecommendation(
            @Valid @RequestBody RecommendationCreateRequest request
    ) {
        // 추천 결과 생성 API
        return recommendationService.createRecommendation(request);
    }

    @GetMapping("/users/{userId}")
    public List<RecommendationResponse> getRecommendationsByUser(
            @PathVariable Long userId
    ) {
        // 사용자 기준 추천 결과 목록 조회 API
        return recommendationService.getRecommendationsByUser(userId);
    }
}