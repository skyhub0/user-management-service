package com.inspire.common.contract.exception;

/**
 * 여기는 공통 에러 코드만 정의
 * 각자 서비스에서 정의할 것
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
