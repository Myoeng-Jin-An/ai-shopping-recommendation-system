package com.aishop.backend.domain.behavior.repository;

import com.aishop.backend.domain.behavior.entity.UserBehaviorLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBehaviorLogRepository extends JpaRepository<UserBehaviorLog, Long> {

    // 사용자 id 기준 행동 로그 목록 조회 로직
    List<UserBehaviorLog> findByUserId(Long userId);

    // 상품 id 기준 행동 로그 목록 조회 로직
    List<UserBehaviorLog> findByProductId(Long productId);

    // 세션 id 기준 행동 로그 목록 조회 로직
    List<UserBehaviorLog> findBySessionId(String sessionId);
}
