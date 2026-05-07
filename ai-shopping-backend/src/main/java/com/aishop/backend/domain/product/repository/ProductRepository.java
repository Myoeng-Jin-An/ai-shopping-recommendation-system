package com.aishop.backend.domain.product.repository;

import com.aishop.backend.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product DB 접근 역할
 * 상품 저장, 조회, 목록 조회를 처리
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}