package com.aishop.backend.domain.order.dto.requestDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문 상품 생성 요청 DTO
 * 클라이언트에서 주문 상품 정보를 전달하는 데이터 역할
 */
@Getter
@NoArgsConstructor
public class OrderItemCreateRequest {

    // 주문 상품 id
    @NotNull(message = "상품 id는 필수입니다.")
    private Long productId;

    // 주문 수량
    @NotNull(message = "주문 수량은 필수입니다.")
    @Min(value = 1, message = "주문 수량은 1개 이상이어야 합니다.")
    private Integer quantity;
}