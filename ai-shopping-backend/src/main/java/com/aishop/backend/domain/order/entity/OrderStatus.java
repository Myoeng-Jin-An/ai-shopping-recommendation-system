package com.aishop.backend.domain.order.entity;

/**
 * 주문 상태 Enum
 * 주문의 진행 상태를 제한하는 역할
 * 주문 처리 흐름과 결제 상태 관리 기준값으로 사용
 */
public enum OrderStatus {
    PENDING,    // 주문 생성 상태
    PAID,    // 결제 완료 상태
    CANCELLED,    // 주문 취소 상태
    REFUNDED    // 환불 완료 상태

}
