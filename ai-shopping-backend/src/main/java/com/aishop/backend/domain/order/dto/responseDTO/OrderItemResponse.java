package com.aishop.backend.domain.order.dto.responseDTO;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 주문 상품 응답 DTO
 * 서버에서 주문 상품 정보를 클라이언트로 반환하는 데이터 역할
 */
@Getter
@Builder
public class OrderItemResponse {

    // 주문 상품 id
    private Long id;

    // 상품 id
    private Long productId;

    // 상품명
    private String productName;

    // 주문 수량
    private Integer quantity;

    // 주문 당시 상품 가격
    private BigDecimal price;
}