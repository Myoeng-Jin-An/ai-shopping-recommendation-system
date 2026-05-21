package com.aishop.backend.domain.anomaly.converter;

import com.aishop.backend.domain.anomaly.dto.responseDTO.AnomalyLogResponse;
import com.aishop.backend.domain.anomaly.entity.AnomalyLog;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.user.entity.User;

/**
 * 이상 행동 로그 Converter
 * 이상 행동 로그 Entity와 DTO 간 변환 역할
 */
public class AnomalyLogConverter {

    public static AnomalyLog toEntity(
            User user,
            Product product,
            String reason,
            Float score
    ) {
        // 이상 행동 로그 Entity 생성 로직
        return AnomalyLog.builder()
                .user(user)
                .product(product)
                .reason(reason)
                .score(score)
                .build();
    }

    public static AnomalyLogResponse toResponse(AnomalyLog anomalyLog) {
        // 이상 행동 상품 null 처리 로직
        Long productId = anomalyLog.getProduct() == null
                ? null
                : anomalyLog.getProduct().getId();

        String productName = anomalyLog.getProduct() == null
                ? null
                : anomalyLog.getProduct().getName();

        // 이상 행동 로그 응답 DTO 변환 로직
        return AnomalyLogResponse.builder()
                .id(anomalyLog.getId())
                .userId(anomalyLog.getUser().getId())
                .productId(productId)
                .productName(productName)
                .reason(anomalyLog.getReason())
                .score(anomalyLog.getScore())
                .createdAt(anomalyLog.getCreatedAt())
                .build();
    }
}