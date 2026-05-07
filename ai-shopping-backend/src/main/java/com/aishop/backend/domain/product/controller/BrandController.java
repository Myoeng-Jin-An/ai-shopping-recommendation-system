package com.aishop.backend.domain.product.controller;

import com.aishop.backend.domain.product.dto.requestDTO.BrandCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.BrandResponse;
import com.aishop.backend.domain.product.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Brand API 요청/응답 처리 역할
 * 브랜드 생성, 브랜드 목록 조회 요청을 Service 계층으로 전달하고
 * 처리 결과를 HTTP 응답으로 반환
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    /**
     * 브랜드 생성 API
     */
    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(
            @Valid @RequestBody BrandCreateRequest request
    ) {
        BrandResponse response = brandService.createBrand(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 브랜드 목록 조회 API
     */
    @GetMapping
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandResponse> response = brandService.getBrands();

        return ResponseEntity.ok(response);
    }
}