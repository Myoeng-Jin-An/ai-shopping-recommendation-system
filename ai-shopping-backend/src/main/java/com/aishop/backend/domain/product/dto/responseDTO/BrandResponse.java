package com.aishop.backend.domain.product.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;

/**
 * 브랜드 응답 DTO
 * 클라이언트에 필요한 브랜드 정보만 반환하는 역할
 */
@Getter
@Builder
public class BrandResponse {

    private Long id;
    private String name;
}