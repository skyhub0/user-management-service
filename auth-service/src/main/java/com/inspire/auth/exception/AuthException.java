package com.inspire.auth.exception;

import com.inspire.common.core.exception.BaseException;
import com.inspire.common.core.exception.ErrorCode;

public class AuthException extends BaseException {

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
