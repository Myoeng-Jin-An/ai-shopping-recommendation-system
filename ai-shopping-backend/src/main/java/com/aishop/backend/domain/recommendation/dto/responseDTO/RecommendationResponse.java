package com.aishop.backend.domain.recommendation.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 추천 결과 응답 DTO
 * 서버에서 추천 결과 정보를 클라이언트로 반환하는 데이터 역할
 */
@Getter
@Builder
public class RecommendationResponse {

    // 추천 결과 id
    private Long id;

    // 추천 대상 사용자 id
    private Long userId;

    // 추천 상품 id
    private Long productId;

    // 추천 상품명
    private String productName;

    // 추천 점수
    private Float score;

    // 추천 결과 생성 시간
    private LocalDateTime createdAt;
}