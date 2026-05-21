package com.aishop.backend.domain.anomaly.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 이상 행동 로그 응답 DTO
 * 서버에서 이상 행동 로그 정보를 클라이언트로 반환하는 데이터 역할
 */
@Getter
@Builder
public class AnomalyLogResponse {

    // 이상 행동 로그 id
    private Long id;

    // 이상 행동 사용자 id
    private Long userId;

    // 이상 행동 상품 id
    private Long productId;

    // 이상 행동 상품명
    private String productName;

    // 이상 행동 탐지 사유
    private String reason;

    // 이상 행동 점수
    private Float score;

    // 이상 행동 로그 생성 시간
    private LocalDateTime createdAt;
}