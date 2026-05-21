package com.aishop.backend.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * API 에러 코드 정의
 * 공통 에러와 도메인별 에러를 한 곳에서 관리하여
 * 예외 응답의 HTTP 상태와 메시지를 일관되게 처리
 */
@Getter
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_400", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 내부 오류가 발생했습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_404", "사용자를 찾을 수 없습니다."),
    USER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER_409", "이미 사용 중인 이메일입니다."),

    BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "BRAND_404", "브랜드를 찾을 수 없습니다."),
    BRAND_ALREADY_EXISTS(HttpStatus.CONFLICT, "BRAND_409", "이미 존재하는 브랜드입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY_404", "카테고리를 찾을 수 없습니다."),
    CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "CATEGORY_409", "이미 존재하는 카테고리입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_404", "상품을 찾을 수 없습니다."),

    // Behavior
    SESSION_NOT_FOUND(HttpStatus.NOT_FOUND, "SESSION_001", "세션을 찾을 수 없습니다."),
    BEHAVIOR_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "BEHAVIOR_001", "행동 로그를 찾을 수 없습니다."),

    // Order
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER_404", "주문을 찾을 수 없습니다."),

    // Recommendation
    RECOMMENDATION_NOT_FOUND(HttpStatus.NOT_FOUND, "RECOMMENDATION_404", "추천 결과를 찾을 수 없습니다."),
    RECOMMENDATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "RECOMMENDATION_409", "이미 존재하는 추천 결과입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
