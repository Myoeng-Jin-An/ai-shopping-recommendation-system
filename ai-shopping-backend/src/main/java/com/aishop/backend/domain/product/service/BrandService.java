package com.aishop.backend.domain.product.service;

import com.aishop.backend.domain.product.converter.BrandConverter;
import com.aishop.backend.domain.product.dto.requestDTO.BrandCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.BrandResponse;
import com.aishop.backend.domain.product.entity.Brand;
import com.aishop.backend.domain.product.repository.BrandRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Brand 도메인 비즈니스 로직
 * 브랜드 생성, 브랜드 목록 조회 등
 * 상품 기준 데이터 관리를 처리
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {

    private final BrandRepository brandRepository;

    /**
     * 브랜드 생성 로직
     * 브랜드명은 unique 컬럼이므로
     * 저장 전 중복 여부를 검증
     */
    @Transactional
    public BrandResponse createBrand(BrandCreateRequest request) {
        validateDuplicateBrandName(request.getName());

        Brand brand = BrandConverter.toEntity(request);
        Brand savedBrand = brandRepository.save(brand);

        return BrandConverter.toResponse(savedBrand);
    }

    /**
     * 브랜드 목록 조회 로직
     */
    public List<BrandResponse> getBrands() {
        return brandRepository.findAll()
                .stream()
                .map(BrandConverter::toResponse)
                .toList();
    }

    /**
     * 브랜드명 중복 검증 로직
     */
    private void validateDuplicateBrandName(String name) {
        if (brandRepository.existsByName(name)) {
            throw new ProductException(ErrorCode.BRAND_ALREADY_EXISTS);
        }
    }
}