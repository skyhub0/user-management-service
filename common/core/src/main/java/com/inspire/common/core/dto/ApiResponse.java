package com.inspire.common.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inspire.common.core.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @param <T>
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    /**
     *
     */
    private boolean success;
    /**
     *
     */
    private int status;
    /**
     *
     */
    private T data;
    /**
     *
     */
    private ErrorResponse error;
    /**
     *
     */
    private LocalDateTime timestamp;

    /**
     *
     * @param success
     * @param status
     * @param data
     * @param error
     * @param timestamp
     */
    private ApiResponse(boolean success, int status, T data, ErrorResponse error, LocalDateTime timestamp) {
        this.success = success;
        this.status = status;
        this.data = data;
        this.error = error;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }

    /**
     *
     * @param status
     * @param data
     * @param timestamp
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofSuccess(int status, T data, LocalDateTime timestamp) {
        return new ApiResponse<>(true, status, data, null, timestamp);
    }

    /**
     *
     * @param status
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofSuccess(int status, T data) {
        return new ApiResponse<>(true, status, data, null, null);
    }

    /**
     *
     * @param data
     * @param timestamp
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofSuccess(T data, LocalDateTime timestamp) {
        return new ApiResponse<>(true, 200, data, null, timestamp);
    }

    /**
     *
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofSuccess(T data) {
        return new ApiResponse<>(true, 200, data, null, null);
    }

    /**
     *
     * @param status
     * @param error
     * @param timestamp
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(int status, ErrorResponse error, LocalDateTime timestamp) {
        return new ApiResponse<>(false, status, null, error, timestamp);
    }

    /**
     *
     * @param status
     * @param error
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(int status, ErrorResponse error) {
        return new ApiResponse<>(false, status, null, error, null);
    }

    /**
     *
     * @param status
     * @param code
     * @param message
     * @param timestamp
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(int status, String code, String message, LocalDateTime timestamp) {
        return new ApiResponse<>(false, status, null, ErrorResponse.of(code, message), timestamp);
    }

    /**
     *
     * @param status
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(int status, String code, String message) {
        return new ApiResponse<>(false, status, null, ErrorResponse.of(code, message), null);
    }

    public static <T> ApiResponse<T> ofError(int status, String code, List<String> messages, LocalDateTime timestamp) {
        return new ApiResponse<>(false, status, null, ErrorResponse.of(code, messages), timestamp);
    }

    /**
     *
     * @param status
     * @param code
     * @param messages
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(int status, String code, List<String> messages) {
        return new ApiResponse<>(false, status, null, ErrorResponse.of(code, messages), null);
    }

    /**
     *
     * @param errorCode
     * @param timestamp
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(ErrorCode errorCode, LocalDateTime timestamp) {
        int status = errorCode.getStatus();
        String code = errorCode.getCode();
        String message = errorCode.getMessage();
        return new ApiResponse<>(false, status, null, ErrorResponse.of(code, message), timestamp);
    }

    /**
     *
     * @param errorCode
     * @return
     * @param <T>
     */
    public static <T> ApiResponse<T> ofError(ErrorCode errorCode) {
        int status = errorCode.getStatus();
        String code = errorCode.getCode();;
        String message = errorCode.getMessage();
        return new ApiResponse<>(false, status, null, ErrorResponse.of(code, message), null);
    }
}
