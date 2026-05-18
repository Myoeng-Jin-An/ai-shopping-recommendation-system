package com.aishop.backend.domain.behavior.entity;

import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 행동 로그 Entity
 * 사용자가 상품에 대해 수행한 행동을 저장하는 역할
 * 추천 시스템과 이상탐지 기능의 입력 데이터로 사용
 */
@Getter
@Entity
@Table(name = "user_behavior_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBehaviorLog {

    // 행동 로그 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 행동 로그 사용자 연관관계
     * 여러 행동 로그가 하나의 사용자에 속하므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 행동 로그 상품 연관관계
     * 여러 행동 로그가 하나의 상품에 속하므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 사용자 행동 타입
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false, length = 20)
    private ActionType actionType;

    // 행동 지속 시간
    @Column
    private Integer duration;

    /**
     * 행동 로그 세션 연관관계
     * 여러 행동 로그가 하나의 세션에 속하므로 ManyToOne 사용
     * 세션 없이 저장되는 로그를 고려하여 nullable 허용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    // 사용자 ip 주소
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    // 행동 로그 생성 시간
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public UserBehaviorLog(
            User user,
            Product product,
            ActionType actionType,
            Integer duration,
            Session session,
            String ipAddress
    ) {
        this.user = user;
        this.product = product;
        this.actionType = actionType;
        this.duration = duration;
        this.session = session;
        this.ipAddress = ipAddress;
    }

    @PrePersist
    protected void onCreate() {
        // 행동 로그 생성 시간 자동 저장 로직
        this.createdAt = LocalDateTime.now();
    }
}