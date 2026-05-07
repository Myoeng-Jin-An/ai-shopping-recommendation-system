package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;

/**
 * User 도메인 예외
 * 사용자 조회 실패, 이메일 중복 등
 * User 도메인에서 발생하는 비즈니스 예외를 표현하는 역할
 */
public class UserException extends BusinessException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
