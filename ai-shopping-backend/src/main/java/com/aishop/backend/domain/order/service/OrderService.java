package com.aishop.backend.domain.order.service;

import com.aishop.backend.domain.order.converter.OrderConverter;
import com.aishop.backend.domain.order.dto.requestDTO.OrderCreateRequest;
import com.aishop.backend.domain.order.dto.requestDTO.OrderItemCreateRequest;
import com.aishop.backend.domain.order.dto.responseDTO.OrderResponse;
import com.aishop.backend.domain.order.entity.Order;
import com.aishop.backend.domain.order.entity.OrderItem;
import com.aishop.backend.domain.order.repository.OrderItemRepository;
import com.aishop.backend.domain.order.repository.OrderRepository;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.product.repository.ProductRepository;
import com.aishop.backend.domain.user.entity.User;
import com.aishop.backend.domain.user.repository.UserRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.OrderException;
import com.aishop.backend.global.error.exception.ProductException;
import com.aishop.backend.global.error.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 주문 Service
 * 주문 생성 및 조회 비즈니스 로직 처리 역할
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse createOrder(OrderCreateRequest request) {
        // 주문 사용자 조회 로직
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        // 주문 총액 계산 로직
        BigDecimal totalPrice = calculateTotalPrice(request.getOrderItems());

        // 주문 Entity 생성 로직
        Order order = OrderConverter.toOrderEntity(user, totalPrice);

        // 주문 저장 로직
        Order savedOrder = orderRepository.save(order);

        // 주문 상품 Entity 목록 생성 로직
        List<OrderItem> orderItems = request.getOrderItems().stream()
                .map(orderItemRequest -> createOrderItem(savedOrder, orderItemRequest))
                .toList();

        // 주문 상품 목록 저장 로직
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);

        // 주문 응답 DTO 변환 로직
        return OrderConverter.toOrderResponse(savedOrder, savedOrderItems);
    }

    public OrderResponse getOrder(Long orderId) {
        // 주문 조회 로직
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));

        // 주문 상품 목록 조회 로직
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        // 주문 응답 DTO 변환 로직
        return OrderConverter.toOrderResponse(order, orderItems);
    }

    public List<OrderResponse> getOrdersByUser(Long userId) {
        // 사용자 존재 여부 검증 로직
        if (!userRepository.existsById(userId)) {
            throw new UserException(ErrorCode.USER_NOT_FOUND);
        }

        // 사용자 기준 주문 목록 조회 로직
        return orderRepository.findByUserId(userId).stream()
                .map(order -> {
                    List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
                    return OrderConverter.toOrderResponse(order, orderItems);
                })
                .toList();
    }

    private BigDecimal calculateTotalPrice(List<OrderItemCreateRequest> orderItemRequests) {
        // 주문 상품 목록 기준 총액 계산 로직
        return orderItemRequests.stream()
                .map(orderItemRequest -> {
                    Product product = productRepository.findById(orderItemRequest.getProductId())
                            .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

                    return product.getPrice()
                            .multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private OrderItem createOrderItem(Order order, OrderItemCreateRequest request) {
        // 주문 상품의 상품 조회 로직
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        // 주문 상품 Entity 생성 로직
        return OrderConverter.toOrderItemEntity(
                order,
                product,
                request.getQuantity()
        );
    }
}