package com.aishop.backend.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aishop.backend.domain.product.entity.Brand;

import java.util.Optional;

/**
 * Brand DB 접근 역할
 * 브랜드 저장, 조회, 중복 확인을 처리
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    boolean existsByName(String name);
}