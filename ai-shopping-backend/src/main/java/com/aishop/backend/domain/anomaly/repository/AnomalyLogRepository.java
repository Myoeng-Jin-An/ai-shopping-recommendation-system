package com.aishop.backend.domain.anomaly.repository;

import com.aishop.backend.domain.anomaly.entity.AnomalyLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 이상 행동 로그 Repository
 * 이상 행동 로그 Entity의 DB 접근을 담당하는 역할
 */
public interface AnomalyLogRepository extends JpaRepository<AnomalyLog, Long> {

    // 사용자 id 기준 이상 행동 로그 목록 최신순 조회 로직
    List<AnomalyLog> findByUserIdOrderByCreatedAtDesc(Long userId);
}