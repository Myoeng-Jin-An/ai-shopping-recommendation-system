package com.aishop.backend.domain.product.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 브랜드 생성 요청 DTO
 * 클라이언트가 브랜드 생성 API로 전달하는 입력값을 담는 역할
 */
@Getter
@NoArgsConstructor
public class BrandCreateRequest {

    @NotBlank(message = "브랜드명은 필수입니다.")
    private String name;
}