package com.aishop.backend.domain.order.repository;

import com.aishop.backend.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 주문 Repository
 * 주문 Entity의 DB 접근을 담당하는 역할
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 사용자 id 기준 주문 목록 조회 로직
    List<Order> findByUserId(Long userId);
}