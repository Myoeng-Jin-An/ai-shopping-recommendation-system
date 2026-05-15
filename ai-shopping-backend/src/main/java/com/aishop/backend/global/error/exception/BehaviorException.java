package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;

public class BehaviorException extends BusinessException {

    public BehaviorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
