package com.aishop.backend.domain.order.dto.requestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 주문 생성 요청 DTO
 * 클라이언트에서 주문 생성 시 전달하는 데이터 역할
 */
@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    // 주문 사용자 id
    @NotNull(message = "사용자 id는 필수입니다.")
    private Long userId;

    // 주문 상품 목록
    @Valid
    @NotEmpty(message = "주문 상품은 최소 1개 이상이어야 합니다.")
    private List<OrderItemCreateRequest> orderItems;
}