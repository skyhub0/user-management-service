package com.inspire.common.jwt.exception;

import com.inspire.common.core.exception.ErrorCode;

public enum JwtErrorCode implements ErrorCode {

    EXPIRED_JWT(401, "JWT001", "JWT expired"),
    INVALID_SIGNATURE(401, "JWT002", "Invalid JWT signature"),
    UNSUPPORTED_OR_MALFORMED(401, "JWT003", "Unsupported or malformed JWT received");

    private final int status;
    private final String code;
    private final String message;

    JwtErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
