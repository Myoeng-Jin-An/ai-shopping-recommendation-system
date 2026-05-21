package com.aishop.backend.domain.recommendation.dto.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 추천 결과 생성 요청 DTO
 * 클라이언트 또는 AI 서버에서 추천 결과 생성 시 전달하는 데이터 역할
 */
@Getter
@NoArgsConstructor
public class RecommendationCreateRequest {

    // 추천 대상 사용자 id
    @NotNull(message = "사용자 id는 필수입니다.")
    private Long userId;

    // 추천 대상 상품 id
    @NotNull(message = "상품 id는 필수입니다.")
    private Long productId;

    // 추천 점수
    @NotNull(message = "추천 점수는 필수입니다.")
    private Float score;
}
