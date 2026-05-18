package com.aishop.backend.domain.order.dto.responseDTO;

import com.aishop.backend.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 주문 응답 DTO
 * 서버에서 주문 정보를 클라이언트로 반환하는 데이터 역할
 */
@Getter
@Builder
public class OrderResponse {

    // 주문 id
    private Long id;

    // 주문 사용자 id
    private Long userId;

    // 주문 총액
    private BigDecimal totalPrice;

    // 주문 상태
    private OrderStatus status;

    // 주문 상품 목록
    private List<OrderItemResponse> orderItems;

    // 주문 생성 시간
    private LocalDateTime createdAt;

    // 주문 수정 시간
    private LocalDateTime updatedAt;
}