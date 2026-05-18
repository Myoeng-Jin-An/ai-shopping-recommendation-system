package com.aishop.backend.domain.order.entity;

import com.aishop.backend.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 주문 상품 Entity
 * 하나의 주문에 포함된 상품 정보를 저장하는 역할
 * 주문 당시 상품 가격과 수량을 snapshot으로 관리
 */
@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    // 주문 상품 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문 연관관계
     * 여러 주문 상품이 하나의 주문에 속하므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * 상품 연관관계
     * 여러 주문 상품이 하나의 상품을 참조할 수 있으므로 ManyToOne 사용
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 주문 수량
    @Column(nullable = false)
    private Integer quantity;

    // 주문 당시 상품 가격 snapshot
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Builder
    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}