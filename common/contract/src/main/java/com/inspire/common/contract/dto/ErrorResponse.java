package com.inspire.common.contract.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    /**
     *
     */
    private String code;
    /**
     *
     */
    private String message;

    /**
     *
     * @param code
     * @param message
     */
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getCode() {
        return this.code;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return this.message;
    }
}
