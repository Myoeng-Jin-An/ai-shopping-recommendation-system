package com.aishop.backend.domain.product.converter;

import com.aishop.backend.domain.product.dto.requestDTO.BrandCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.BrandResponse;
import com.aishop.backend.domain.product.entity.Brand;

/**
 * Brand DTO와 Entity 변환 역할
 * 브랜드 생성 요청과 브랜드 응답 변환을 분리하여
 * Service 계층의 비즈니스 흐름을 단순하게 유지
 */
public class BrandConverter {

    private BrandConverter() {
    }

    /**
     * 브랜드 생성 요청 DTO를 Brand Entity로 변환하는 로직
     */
    public static Brand toEntity(BrandCreateRequest request) {
        return Brand.builder()
                .name(request.getName())
                .build();
    }

    /**
     * Brand Entity를 브랜드 응답 DTO로 변환하는 로직
     */
    public static BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
