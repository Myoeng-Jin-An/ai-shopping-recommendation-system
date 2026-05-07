package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;

/**
 * Product 도메인 예외
 * 상품, 브랜드, 카테고리 조회 실패 등
 * Product 도메인에서 발생하는 비즈니스 예외를 표현하는 역할
 */
public class ProductException extends BusinessException  {

    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }
}
