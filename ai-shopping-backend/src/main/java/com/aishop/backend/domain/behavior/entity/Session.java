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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "sessions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "started_at")
    LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @PrePersist
    protected void onCreate() {
        // 세션 ID 자동 생성 로직
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
