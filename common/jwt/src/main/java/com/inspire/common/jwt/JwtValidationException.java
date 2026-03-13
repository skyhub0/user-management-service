package com.inspire.common.jwt;

/**
 * {@code RuntimeException} thrown when a JWT token fails validation.
 *
 * <p>
 * This {@code RuntimeException} is used to indicate issues such as expired tokens,
 * invalid signatures, or unsupported/malformed JWTs during parsing or validation.
 *
 * <hr>
 *
 * 유효성 검증에 실패했을 때 발생하는 {@code RuntimeException}
 *
 * <p>
 * 유효성 검증 과정에서 토큰의 만료, 유효하지 않은 서명, 지원하지 않는 형식의 JWT를 나타내기 위한 {@code RuntimeException}
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @since 1.0.0
 */
public class JwtValidationException extends RuntimeException {

    /**
     * Constructs a new {@code JwtValidationException} instance with {@code null} as its detail message.
     *
     * <hr>
     *
     * 상세 메시지가 없는 {@code JwtValidationException}을 생성합니다.
     */
    public JwtValidationException() {}

    /**
     * Constructs a new {@code JwtValidationException} with the specified detail message.
     *
     * <hr>
     *
     * 상세 메시지를 포함하여 {@code JwtValidationException}을 생성합니다.
     *
     * <hr>
     *
     * @param message the detail message describing the validation error
     *                (유효성 에러를 설명하는 상세 메시지)
     */
    public JwtValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code JwtValidationException} with the specified detail message and cause.
     *
     * <hr>
     *
     * 상세 메시지 및 원인을 포함하여 {@code JwtValidationException}을 생성합니다.
     *
     * <hr>
     *
     * @param message the detail message describing the validation error
     *                (유효성 에러를 설명하는 상세 메시지)
     * @param cause the underlying cause of the exception
     *              (에러가 발생한 근본 원인)
     */
    public JwtValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
