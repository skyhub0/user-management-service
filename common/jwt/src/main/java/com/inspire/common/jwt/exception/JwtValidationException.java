package com.inspire.common.jwt.exception;

import com.inspire.common.core.exception.BaseException;
import com.inspire.common.core.exception.ErrorCode;

/**
 * {@link BaseException} thrown when a JWT token fails validation.
 *
 * <p>
 * This {@link BaseException} is used to indicate issues such as expired tokens,
 * invalid signatures, or unsupported/malformed JWTs during parsing or validation.
 *
 * <hr>
 *
 * 유효성 검증에 실패했을 때 발생하는 {@link BaseException}
 *
 * <p>
 * 유효성 검증 과정에서 토큰의 만료, 유효하지 않은 서명, 지원하지 않는 형식의 JWT를 나타내기 위한 {@link BaseException}
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @see BaseException
 * @since 1.0.0
 */
public class JwtValidationException extends BaseException {

    /**
     * Constructs a new {@code JwtValidationException} instance with {@link ErrorCode}.
     *
     * <hr>
     *
     * {@link ErrorCode}를 사용하여 {@code JwtValidationException} 인스턴스를 생성합니다.
     *
     * <hr>
     *
     * @param errorCode the error code describing the validation error
     *                  (유효성 에러를 설명하는 에러 코드)
     * @see ErrorCode
     */
    public JwtValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * Constructs a new {@code JwtValidationException} instance with the {@link ErrorCode} and cause.
     *
     * <hr>
     *
     * {@link ErrorCode} 및 원인을 포함하여 {@code JwtValidationException}을 생성합니다.
     *
     * <hr>
     *
     * @param errorCode the error code describing the validation error
     *                  (유효성 에러를 설명하는 에러 코드)
     * @param cause     the underlying cause of the exception
     *                  (에러가 발생한 근본 원인)
     * @see ErrorCode
     */
    public JwtValidationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
