package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;
import lombok.Getter;

/**
 * 비즈니스 예외의 최상위 클래스
 * 도메인 규칙 위반, 데이터 미존재 등
 * 애플리케이션에서 의도적으로 발생시키는 예외의 공통 부모 역할
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
