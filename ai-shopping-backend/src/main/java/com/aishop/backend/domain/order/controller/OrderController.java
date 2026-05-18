package com.aishop.backend.domain.order.controller;

import com.aishop.backend.domain.order.dto.requestDTO.OrderCreateRequest;
import com.aishop.backend.domain.order.dto.responseDTO.OrderResponse;
import com.aishop.backend.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 주문 Controller
 * 주문 API 요청과 응답을 처리하는 역할
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(
            @Valid @RequestBody OrderCreateRequest request
    ) {
        // 주문 생성 API
        return orderService.createOrder(request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(
            @PathVariable Long orderId
    ) {
        // 주문 단건 조회 API
        return orderService.getOrder(orderId);
    }

    @GetMapping("/users/{userId}")
    public List<OrderResponse> getOrdersByUser(
            @PathVariable Long userId
    ) {
        // 사용자 기준 주문 목록 조회 API
        return orderService.getOrdersByUser(userId);
    }
}