package com.aishop.backend.domain.behavior.dto.requestDTO;

import com.aishop.backend.domain.behavior.entity.ActionType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 행동 로그 생성 요청 DTO
 * 클라이언트에서 행동 로그 생성 시 전달하는 데이터 역할
 */
@Getter
@NoArgsConstructor
public class BehaviorLogCreateRequest {

    // 행동 사용자 id
    @NotNull(message = "사용자 id는 필수입니다.")
    private Long userId;

    // 행동 상품 id
    @NotNull(message = "상품 id는 필수입니다.")
    private Long productId;

    // 사용자 행동 타입
    @NotNull(message = "행동 타입은 필수입니다.")
    private ActionType actionType;

    // 행동 지속 시간
    private Integer duration;

    // 세션 id
    private String sessionId;

    // 사용자 ip 주소
    private String ipAddress;
}
