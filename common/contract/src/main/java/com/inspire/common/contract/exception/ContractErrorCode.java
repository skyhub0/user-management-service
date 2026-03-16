package com.inspire.common.contract.exception;

import com.inspire.common.core.exception.ErrorCode;

/**
 *
 */
public enum ContractErrorCode implements ErrorCode {

    ;

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getCode() {
        return "";
    }

    @Override
    public String getMessage() {
        return "";
    }
}
