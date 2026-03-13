package com.inspire.common.contract.exception;

public class TestException extends BaseException {

    public TestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TestException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
