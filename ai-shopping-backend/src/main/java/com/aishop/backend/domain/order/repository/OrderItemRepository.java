package com.aishop.backend.domain.order.repository;

import com.aishop.backend.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 주문 상품 Repository
 * 주문 상품 Entity의 DB 접근을 담당하는 역할
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // 주문 id 기준 주문 상품 목록 조회 로직
    List<OrderItem> findByOrderId(Long orderId);
}