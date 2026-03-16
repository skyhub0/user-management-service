package com.inspire.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

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
     */
    private List<String> messages;

    /**
     *
     * @param code
     * @param message
     */
    private ErrorResponse(String code, String message, List<String> messages) {
        this.code = code;
        this.message = message;
        this.messages = messages;
    }

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, null);
    }

    public static ErrorResponse of(String code, List<String> messages) {
        return new ErrorResponse(code, null, messages);
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

    public List<String> getMessages() {
        return this.messages;
    }
}
