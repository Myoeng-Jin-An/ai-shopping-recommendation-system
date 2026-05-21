package com.aishop.backend.domain.recommendation.entity;

import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 추천 결과 Entity
 * 사용자별 상품 추천 결과를 저장하는 역할
 * AI 추천 로직 또는 외부 AI 서버에서 계산된 추천 점수 관리
 */
@Getter
@Entity
@Table(
        name = "recommendations",
        indexes = {
                @Index(name = "idx_recommendations_user_id", columnList = "user_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_recommendations_user_product",
                        columnNames = {"user_id", "product_id"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recommendation {

    //추천 결과 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 추천 대상 사용자 연관관계
     * 여러 추천 결과가 하나의 사용자에 속하므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 추천 대상 상품 연관관계
     * 여러 추천 결과가 하나의 상품을 참조할 수 있으므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 추천 점수
    @Column(nullable = false)
    private Float score;

    // 추천 결과 생성 시간
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder //자바 문법에서는 Recommendation recommendation = new Recommendation(user, product, 0.95f); 이렇게 사용함
    public Recommendation(User user, Product product, Float score) {
        this.user = user;
        this.product = product;
        this.score = score;
    }

    @PrePersist /**JPA 어노테이션으로 "이 엔티티가 DB에 처음 저장되기 직전에 이 메서드를 자동 실행한다"*/
    protected void onCreate() {
        // 추천 결과 생성 시간 자동 저장 로직
        this.createdAt = LocalDateTime.now();
    }
}
