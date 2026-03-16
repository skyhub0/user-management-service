package com.inspire.common.contract.exception;

import com.inspire.common.core.exception.BaseException;
import com.inspire.common.core.exception.ErrorCode;

public class TestException extends BaseException {

    public TestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TestException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
