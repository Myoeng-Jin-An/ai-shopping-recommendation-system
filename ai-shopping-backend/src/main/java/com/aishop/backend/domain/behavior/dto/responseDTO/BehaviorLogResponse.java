package com.aishop.backend.domain.behavior.dto.responseDTO;

import com.aishop.backend.domain.behavior.entity.ActionType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 행동 로그 응답 DTO
 * 서버에서 행동 로그 정보를 클라이언트로 반환하는 데이터 역할
 */
@Getter
@Builder
public class BehaviorLogResponse {

    // 행동 로그 id
    private Long id;

    // 행동 사용자 id
    private Long userId;

    // 행동 상품 id
    private Long productId;

    // 사용자 행동 타입
    private ActionType actionType;

    // 행동 지속 시간
    private Integer duration;

    // 세션 id
    private String sessionId;

    // 사용자 ip 주소
    private String ipAddress;

    // 행동 로그 생성 시간
    private LocalDateTime createdAt;
}
