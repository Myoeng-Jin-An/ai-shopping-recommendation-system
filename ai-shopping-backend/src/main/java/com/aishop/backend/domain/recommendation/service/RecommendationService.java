package com.aishop.backend.domain.recommendation.service;

import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.product.repository.ProductRepository;
import com.aishop.backend.domain.recommendation.converter.RecommendationConverter;
import com.aishop.backend.domain.recommendation.dto.requestDTO.RecommendationCreateRequest;
import com.aishop.backend.domain.recommendation.dto.responseDTO.RecommendationResponse;
import com.aishop.backend.domain.recommendation.entity.Recommendation;
import com.aishop.backend.domain.recommendation.repository.RecommendationRepository;
import com.aishop.backend.domain.user.entity.User;
import com.aishop.backend.domain.user.repository.UserRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.ProductException;
import com.aishop.backend.global.error.exception.RecommendationException;
import com.aishop.backend.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 추천 결과 Service
 * 추천 결과 생성 및 조회 비즈니스 로직 처리 역할
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public RecommendationResponse createRecommendation(RecommendationCreateRequest request) {
        // 추천 대상 사용자 조회 로직
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        // 추천 대상 상품 조회 로직
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        // 추천 결과 중복 검증 로직
        if (recommendationRepository.existsByUserIdAndProductId(
                request.getUserId(),
                request.getProductId()
        )) {
            throw new RecommendationException(ErrorCode.RECOMMENDATION_ALREADY_EXISTS);
        }

        // 추천 결과 Entity 변환 로직
        Recommendation recommendation = RecommendationConverter.toEntity(
                user,
                product,
                request.getScore()
        );

        // 추천 결과 저장 로직
        Recommendation savedRecommendation = recommendationRepository.save(recommendation);

        // 추천 결과 응답 변환 로직
        return RecommendationConverter.toResponse(savedRecommendation);
    }

    public List<RecommendationResponse> getRecommendationsByUser(Long userId) {
        // 사용자 존재 여부 검증 로직
        if (!userRepository.existsById(userId)) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }

        // 사용자 기준 추천 결과 목록 조회 로직
        return recommendationRepository.findByUserIdOrderByScoreDesc(userId).stream()
                .map(RecommendationConverter::toResponse)
                .toList();
    }
}