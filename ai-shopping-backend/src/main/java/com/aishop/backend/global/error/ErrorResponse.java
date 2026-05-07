package com.aishop.backend.global.error;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * API 에러 응답 DTO
 * 모든 예외 응답의 형태를 통일하여
 * 클라이언트가 에러 코드를 기준으로 분기 처리할 수 있도록 구성
 */
@Getter
@Builder
public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String code;
    private final String message;
    private final List<FieldError> errors;

    public static ErrorResponse from(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(List.of())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }

    /**
     * 요청 필드 검증 실패 상세 정보
     */
    @Getter
    @Builder
    public static class FieldError {

        private final String field;
        private final String rejectedValue;
        private final String message;

        public static FieldError of(org.springframework.validation.FieldError fieldError) {
            Object rejectedValue = fieldError.getRejectedValue();

            return FieldError.builder()
                    .field(fieldError.getField())
                    .rejectedValue(rejectedValue == null ? null : rejectedValue.toString())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
