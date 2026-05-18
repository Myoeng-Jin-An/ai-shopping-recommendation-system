package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;

public class OrderException extends BusinessException {

    public OrderException(ErrorCode errorCode) {
        super(errorCode);
    }
}