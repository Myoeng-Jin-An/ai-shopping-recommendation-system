package com.aishop.backend.domain.product.service;

import com.aishop.backend.domain.product.converter.ProductConverter;
import com.aishop.backend.domain.product.dto.requestDTO.ProductCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.ProductResponse;
import com.aishop.backend.domain.product.entity.Brand;
import com.aishop.backend.domain.product.entity.Category;
import com.aishop.backend.domain.product.entity.Product;
import com.aishop.backend.domain.product.repository.BrandRepository;
import com.aishop.backend.domain.product.repository.CategoryRepository;
import com.aishop.backend.domain.product.repository.ProductRepository;
import com.aishop.backend.global.error.ErrorCode;
import com.aishop.backend.global.error.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Product 도메인 비즈니스 로직
 * 상품 생성, 상품 조회 등 상품 도메인 규칙을 처리
 * Controller는 요청/응답 흐름을 담당하고 Repository는 DB 접근을 담당
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 상품 생성 로직
     * 상품은 Brand, Category와 연관관계를 가지므로
     * 상품 저장 전 브랜드와 카테고리 존재 여부를 먼저 검증
     */
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        Brand brand = getBrand(request.getBrandId());
        Category category = getCategory(request.getCategoryId());

        Product product = ProductConverter.toEntity(request, brand, category);
        Product savedProduct = productRepository.save(product);

        return ProductConverter.toResponse(savedProduct);
    }

    /**
     * 상품 단건 조회 로직
     */
    public ProductResponse getProduct(Long productId) {
        Product product = getProductEntity(productId);

        return ProductConverter.toResponse(product);
    }

    /**
     * 상품 목록 조회 로직
     */
    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductConverter::toResponse)
                .toList();
    }

    /**
     * 브랜드 조회 로직
     * 상품 생성 시 전달받은 brandId가 실제 DB에 존재하는지 검증
     */
    private Brand getBrand(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new ProductException(ErrorCode.BRAND_NOT_FOUND));
    }

    /**
     * 카테고리 조회 로직
     * 상품 생성 시 전달받은 categoryId가 실제 DB에 존재하는지 검증
     */
    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProductException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    /**
     * 상품 Entity 조회 로직
     * 내부 로직에서 Entity가 필요한 경우 재사용
     */
    private Product getProductEntity(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}