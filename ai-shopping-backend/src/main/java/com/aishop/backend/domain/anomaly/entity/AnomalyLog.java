package com.aishop.backend.domain.anomaly.entity;

import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 이상 행동 로그 Entity
 * 사용자 이상 행동 탐지 결과를 저장하는 역할
 * AI 이상 탐지 로직 또는 외부 AI 서버에서 계산된 이상 점수 관리
 */
@Getter
@Entity
@Table(
        name = "anomaly_logs",
        indexes = {
                @Index(name = "idx_anomaly_logs_user_id", columnList = "user_id")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnomalyLog {

    // 이상 행동 로그 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 이상 행동 사용자 연관관계
     * 여러 이상 행동 로그가 하나의 사용자에 속하므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 이상 행동 상품 연관관계
     * 상품 기반 이상 탐지일 때만 상품과 연결되는 선택 관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 이상 행동 탐지 사유
    @Column(length = 255)
    private String reason;

    // 이상 행동 점수
    @Column(nullable = false)
    private Float score;

    // 이상 행동 로그 생성 시간
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public AnomalyLog(User user, Product product, String reason, Float score) {
        this.user = user;
        this.product = product;
        this.reason = reason;
        this.score = score;
    }

    @PrePersist
    protected void onCreate() {
        // 이상 행동 로그 생성 시간 자동 저장 로직
        this.createdAt = LocalDateTime.now();
    }
}
