package com.inspire.common.contract.exception;

/**
 *
 */
public enum CAErrorCode {
    INTERNAL_SERVER_ERROR("CA001"),
    INVALID_REQUEST("CA002"),
    INTERNAL_SERVER_VALIDATION_ERROR("CA003"),
    OTHER_MVC_EXCEPTION("CA004");

    private String code;

    CAErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
