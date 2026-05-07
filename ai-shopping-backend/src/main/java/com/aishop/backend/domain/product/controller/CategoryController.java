package com.aishop.backend.domain.product.controller;

import com.aishop.backend.domain.product.dto.requestDTO.CategoryCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.CategoryResponse;
import com.aishop.backend.domain.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category API 요청/응답 처리 역할
 * 카테고리 생성, 카테고리 목록 조회 요청을 Service 계층으로 전달하고
 * 처리 결과를 HTTP 응답으로 반환
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 생성 API
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryCreateRequest request
    ) {
        CategoryResponse response = categoryService.createCategory(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 카테고리 목록 조회 API
     */
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        List<CategoryResponse> response = categoryService.getCategories();

        return ResponseEntity.ok(response);
    }
}