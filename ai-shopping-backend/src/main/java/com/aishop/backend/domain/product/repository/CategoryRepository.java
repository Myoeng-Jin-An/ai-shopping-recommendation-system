package com.aishop.backend.domain.product.repository;

import com.aishop.backend.domain.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Category DB 접근 역할
 * 카테고리 저장, 조회, 중복 확인을 처리
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    boolean existsByName(String name);
}