package com.aishop.backend.domain.product.converter;

import com.aishop.backend.domain.product.dto.requestDTO.CategoryCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.CategoryResponse;
import com.aishop.backend.domain.product.entity.Category;

/**
 * Category DTO와 Entity 변환 역할
 * 카테고리 생성 요청과 카테고리 응답 변환을 분리하여
 * Service 계층의 비즈니스 흐름을 단순하게 유지
 */
public class CategoryConverter {

    private CategoryConverter() {
    }

    /**
     * 카테고리 생성 요청 DTO를 Category Entity로 변환하는 로직
     */
    public static Category toEntity(CategoryCreateRequest request) {
        return Category.builder()
                .name(request.getName())
                .build();
    }

    /**
     * Category Entity를 카테고리 응답 DTO로 변환하는 로직
     */
    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}