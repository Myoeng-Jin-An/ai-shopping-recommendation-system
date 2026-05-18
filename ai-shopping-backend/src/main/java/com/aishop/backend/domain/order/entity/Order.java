package com.aishop.backend.domain.order.entity;

import com.aishop.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 주문 Entity
 * 사용자의 구매 결과를 저장하는 역할
 * 주문 총액, 주문 상태, 주문 생성/수정 시간을 관리
 */
@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    // 주문 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문 사용자 연관관계
     * 여러 주문이 하나의 사용자에 속하므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 주문 총액
    @Column(name = "total_price", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    // 주문 생성 시간
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 주문 수정 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Order(User user, BigDecimal totalPrice, OrderStatus status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status == null ? OrderStatus.PENDING : status;
    }

    @PrePersist
    protected void onCreate() {
        // 주문 생성 시간 자동 저장 로직
        this.createdAt = LocalDateTime.now();

        // 주문 상태 기본값 설정 로직
        if (this.status == null) {
            this.status = OrderStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        // 주문 수정 시간 자동 저장 로직
        this.updatedAt = LocalDateTime.now();
    }
}