package com.aishop.backend.global.error.exception;

import com.aishop.backend.global.error.ErrorCode;

public class AnomalyException extends BusinessException {

    public AnomalyException(ErrorCode errorCode) {
        super(errorCode);
    }
}