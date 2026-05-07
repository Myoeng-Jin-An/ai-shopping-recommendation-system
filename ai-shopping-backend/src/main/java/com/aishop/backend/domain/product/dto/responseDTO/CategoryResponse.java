package com.aishop.backend.domain.product.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;

/**
 * 카테고리 응답 DTO
 * 클라이언트에 필요한 카테고리 정보만 반환하는 역할
 */
@Getter
@Builder
public class CategoryResponse {

    private Long id;
    private String name;
}