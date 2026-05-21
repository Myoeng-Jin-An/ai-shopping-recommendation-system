package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;

public class RecommendationException extends BusinessException {

    public RecommendationException(ErrorCode errorCode) {
        super(errorCode);
    }
}