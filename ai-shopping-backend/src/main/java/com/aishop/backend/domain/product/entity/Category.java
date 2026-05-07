package com.aishop.backend.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 카테고리 Entity
 * 상품 분류 기준을 관리하며
 * 카테고리 기반 추천 로직의 기준 데이터로 활용
 */
@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}