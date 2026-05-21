package com.aishop.backend.domain.recommendation.converter;

import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.recommendation.dto.responseDTO.RecommendationResponse;
import com.aishop.backend.domain.recommendation.entity.Recommendation;
import com.aishop.backend.domain.user.entity.User;

/**
 * 추천 결과 Converter
 * 추천 결과 Entity와 DTO 간 변환 역할
 */
public class RecommendationConverter {

    public static Recommendation toEntity(User user, Product product, Float score) {
        // 추천 결과 Entity 생성 로직
        return Recommendation.builder()
                .user(user)
                .product(product)
                .score(score)
                .build();
    }

    public static RecommendationResponse toResponse(Recommendation recommendation) {
        // 추천 결과 응답 DTO 변환 로직
        return RecommendationResponse.builder()
                .id(recommendation.getId())
                .userId(recommendation.getUser().getId())
                .productId(recommendation.getProduct().getId())
                .productName(recommendation.getProduct().getName())
                .score(recommendation.getScore())
                .createdAt(recommendation.getCreatedAt())
                .build();
    }
}