package com.aishop.backend.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 브랜드 Entity
 * 브랜드명을 별도 테이블로 정규화하여
 * 상품 데이터 중복을 줄이고 추천 기준으로 활용
 */
@Getter
@Entity
@Table(name = "brands")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

    /*브랜드 id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Builder
    public Brand(String name) {
        this.name = name;
    }
}
