package com.inspire.common.contract.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller Advice가 처리하기 위한
 */
@Slf4j
public abstract class BaseException extends RuntimeException {

    /**
     *
     */
    private final ErrorCode errorCode;

    /**
     *
     * @param errorCode
     */
    protected BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        log.error("BaseException occurred");
    }

    /**
     *
     * @param errorCode
     * @param cause
     */
    protected BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        log.error("BaseException occurred");
    }

    /**
     *
     * @return
     */
    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
