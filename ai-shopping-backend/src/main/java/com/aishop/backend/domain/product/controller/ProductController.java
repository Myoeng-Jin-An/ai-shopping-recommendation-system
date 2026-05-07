package com.aishop.backend.domain.product.controller;

import com.aishop.backend.domain.product.dto.requestDTO.ProductCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.ProductResponse;
import com.aishop.backend.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product API 요청/응답 처리 역할
 * 상품 생성, 상품 단건 조회, 상품 목록 조회 요청을 Service 계층으로 전달하고
 * 처리 결과를 HTTP 응답으로 반환
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 생성 API
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 상품 단건 조회 API
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse response = productService.getProduct(productId);

        return ResponseEntity.ok(response);
    }

    /**
     * 상품 목록 조회 API
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> response = productService.getProducts();

        return ResponseEntity.ok(response);
    }
}