package com.aishop.backend.domain.product.converter;

import com.aishop.backend.domain.product.dto.requestDTO.ProductCreateRequest;
import com.aishop.backend.domain.product.dto.responseDTO.ProductResponse;
import com.aishop.backend.domain.product.entity.Brand;
import com.aishop.backend.domain.product.entity.Category;
import com.aishop.backend.domain.product.entity.Product;

/**
 * Product DTO와 Entity 변환 역할
 * Controller/Service 계층에서 변환 로직이 섞이지 않도록
 * 상품 생성 요청과 상품 응답 변환을 분리
 */
public class ProductConverter {

    private ProductConverter() {
    }

    /**
     * 상품 생성 요청 DTO를 Product Entity로 변환하는 로직
     * request에는 brandId, categoryId만 존재하므로
     * Service에서 조회한 Brand, Category Entity를 함께 전달
     */
    public static Product toEntity(ProductCreateRequest request, Brand brand, Category category) {
        return Product.builder()
                .name(request.getName())
                .brand(brand)
                .category(category)
                .price(request.getPrice())
                .build();
    }

    /**
     * Product Entity를 상품 응답 DTO로 변환하는 로직
     * Entity를 직접 반환하지 않고
     * 클라이언트에 필요한 상품 정보만 응답으로 구성
     */
    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brandId(product.getBrand().getId())
                .brandName(product.getBrand().getName())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
