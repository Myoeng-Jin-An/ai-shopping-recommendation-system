package com.aishop.backend.domain.product.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 상품 응답 DTO
 * Entity를 직접 노출하지 않고
 * 클라이언트에 필요한 상품 정보만 반환하는 역할
 */
@Getter
@Builder
public class ProductResponse {

    private Long id;
    private String name;

    private Long brandId;
    private String brandName;

    private Long categoryId;
    private String categoryName;

    private BigDecimal price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}