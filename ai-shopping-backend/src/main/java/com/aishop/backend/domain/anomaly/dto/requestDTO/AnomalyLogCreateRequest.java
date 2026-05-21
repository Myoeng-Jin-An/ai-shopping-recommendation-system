package com.aishop.backend.domain.anomaly.dto.requestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이상 행동 로그 생성 요청 DTO
 * 클라이언트 또는 AI 서버에서 이상 행동 로그 생성 시 전달하는 데이터 역할
 */
@Getter
@NoArgsConstructor
public class AnomalyLogCreateRequest {

    // 이상 행동 사용자 id
    @NotNull(message = "사용자 id는 필수입니다.")
    private Long userId;

    // 이상 행동 상품 id
    private Long productId;

    // 이상 행동 탐지 사유
    private String reason;

    // 이상 행동 점수
    @NotNull(message = "이상 행동 점수는 필수입니다.")
    private Float score;
}