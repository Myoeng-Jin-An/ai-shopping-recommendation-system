package com.aishop.backend.domain.product.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 생성 요청 DTO
 * 클라이언트가 카테고리 생성 API로 전달하는 입력값을 담는 역할
 */
@Getter
@NoArgsConstructor
public class CategoryCreateRequest {

    @NotBlank(message = "카테고리명은 필수입니다.")
    private String name;
}