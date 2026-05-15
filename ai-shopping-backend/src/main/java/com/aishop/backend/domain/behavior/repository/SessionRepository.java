package com.aishop.backend.domain.behavior.repository;

import com.aishop.backend.domain.behavior.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 세션 Repository
 * 세션 Entity의 DB 접근을 담당하는 역할
 */
public interface SessionRepository extends JpaRepository<Session, String> {

    // 사용자 id 기준 세션 목록 조회 로직
    List<Session> findByUserId(Long userId);
}
