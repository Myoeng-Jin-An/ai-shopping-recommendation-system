package com.aishop.backend.domain.order.converter;

import com.aishop.backend.domain.order.dto.responseDTO.OrderItemResponse;
import com.aishop.backend.domain.order.dto.responseDTO.OrderResponse;
import com.aishop.backend.domain.order.entity.Order;
import com.aishop.backend.domain.order.entity.OrderItem;
import com.aishop.backend.domain.order.entity.OrderStatus;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.user.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * 주문 Converter
 * 주문 Entity와 DTO 간 변환 역할
 */
public class OrderConverter {

    public static Order toOrderEntity(User user, BigDecimal totalPrice) {
        // 주문 Entity 생성 로직
        return Order.builder()
                .user(user)
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .build();
    }

    public static OrderItem toOrderItemEntity(Order order, Product product, Integer quantity) {
        // 주문 상품 Entity 생성 로직
        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .price(product.getPrice())
                .build();
    }

    public static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        // 주문 상품 응답 DTO 변환 로직
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProduct().getName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }

    public static OrderResponse toOrderResponse(
            Order order,
            List<OrderItem> orderItems
    ) {
        // 주문 상품 응답 목록 변환 로직
        List<OrderItemResponse> orderItemResponses = orderItems.stream()
                .map(OrderConverter::toOrderItemResponse)
                .toList();

        // 주문 응답 DTO 변환 로직
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .orderItems(orderItemResponses)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}