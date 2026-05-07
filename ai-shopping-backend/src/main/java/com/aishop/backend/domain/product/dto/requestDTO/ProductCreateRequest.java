package com.aishop.backend.domain.product.dto.requestDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 상품 생성 요청 DTO
 * 클라이언트가 상품 생성 API로 전달하는 입력값을 담는 역할
 */
@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    @NotNull(message = "브랜드 ID는 필수입니다.")
    private Long brandId;

    @NotBlank(message = "카테고리 ID는 필수입니다.")
    private Long categoryId;

    @NotNull(message = "상품 가격은 필수입니다.")
    @DecimalMin(value = "0", inclusive = false, message = "상품 가격은 0보다 커야 합니다.")
    private BigDecimal price;
}
