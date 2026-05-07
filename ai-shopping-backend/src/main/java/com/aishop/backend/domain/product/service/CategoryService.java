package com.aishop.backend.domain.product.service;

import com.aishop.backend.domain.product.converter.CategoryConverter;
import com.aishop.backend.domain.product.dto.requestDTO.CategoryCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.CategoryResponse;
import com.aishop.backend.domain.product.entity.Category;
import com.aishop.backend.domain.product.repository.CategoryRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Category 도메인 비즈니스 로직
 * 카테고리 생성, 카테고리 목록 조회 등
 * 상품 분류 기준 데이터 관리를 처리
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 생성 로직
     * 동일한 카테고리명이 중복 저장되지 않도록
     * 저장 전 중복 여부를 검증
     */
    @Transactional
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        validateDuplicateCategoryName(request.getName());

        Category category = CategoryConverter.toEntity(request);
        Category savedCategory = categoryRepository.save(category);

        return CategoryConverter.toResponse(savedCategory);
    }

    /**
     * 카테고리 목록 조회 로직
     */
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryConverter::toResponse)
                .toList();
    }

    /**
     * 카테고리명 중복 검증 로직
     */
    private void validateDuplicateCategoryName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ProductException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
    }
}