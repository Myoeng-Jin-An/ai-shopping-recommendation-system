package com.aishop.backend.domain.recommendation.repository;

import com.aishop.backend.domain.recommendation.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 추천 결과 Repository
 * 추천 결과 Entity의 DB 접근을 담당하는 역할
 */
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    // 사용자 id 기준 추천 결과 목록 조회 로직
    List<Recommendation> findByUserIdOrderByScoreDesc(Long userId);

    // 사용자와 상품 기준 추천 결과 중복 확인 로직
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}