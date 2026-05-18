package com.aishop.backend.domain.behavior.converter;

import com.aishop.backend.domain.behavior.dto.responseDTO.BehaviorLogResponse;
import com.aishop.backend.domain.behavior.entity.ActionType;
import com.aishop.backend.domain.behavior.entity.Session;
import com.aishop.backend.domain.behavior.entity.UserBehaviorLog;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.user.entity.User;

/**
 * 행동 로그 Converter
 * 행동 로그 Entity와 DTO 간 변환 역할
 */
public class BehaviorLogConverter {

    public static UserBehaviorLog toEntity(
            User user,
            Product product,
            ActionType actionType,
            Integer duration,
            Session session,
            String ipAddress
    ) {
        // 행동 로그 Entity 생성 로직
        return UserBehaviorLog.builder()
                .user(user)
                .product(product)
                .actionType(actionType)
                .duration(duration)
                .session(session)
                .ipAddress(ipAddress)
                .build();
    }

    public static BehaviorLogResponse toResponse(UserBehaviorLog behaviorLog) {
        // 행동 로그 응답 DTO 변환 로직
        return BehaviorLogResponse.builder()
                .id(behaviorLog.getId())
                .userId(behaviorLog.getUser().getId())
                .productId(behaviorLog.getProduct().getId())
                .actionType(behaviorLog.getActionType())
                .duration(behaviorLog.getDuration())
                .sessionId(
                        behaviorLog.getSession() == null
                                ? null
                                : behaviorLog.getSession().getId()
                )
                .ipAddress(behaviorLog.getIpAddress())
                .createdAt(behaviorLog.getCreatedAt())
                .build();
    }
}
