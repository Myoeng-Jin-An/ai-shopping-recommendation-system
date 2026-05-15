package com.aishop.backend.domain.behavior.entity;

import com.aishop.backend.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 세션 Entity
 * 사용자의 접속 단위를 저장하는 역할
 * 행동 로그를 세션 단위로 묶어 분석하기 위한 기준 데이터
 */
@Getter
@Entity
@Table(name = "sessions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    // 세션 id
    @Id
    @Column(length = 36)
    private String id;

    /**
     * 세션 사용자 연관관계
     * 여러 세션이 하나의 사용자에 속하므로 ManyToOne 사용
     * 비회원 세션 확장을 고려하여 nullable 허용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 세션 시작 시간
    @Column(name = "started_at")
    private LocalDateTime startedAt;

    // 세션 종료 시간
    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Builder
    public Session(User user) {
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        // 세션 id 자동 생성 로직
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }

        // 세션 시작 시간 자동 저장 로직
        if (this.startedAt == null) {
            this.startedAt = LocalDateTime.now();
        }
    }

    public void end() {
        // 세션 종료 시간 저장 로직
        this.endedAt = LocalDateTime.now();
    }
}