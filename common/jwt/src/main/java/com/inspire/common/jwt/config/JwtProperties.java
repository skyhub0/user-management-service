package com.inspire.common.jwt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Holds JWT-related configuration properties.
 *
 * <p>
 * Maps settings with the {@code jwt} prefix in {@code application.yml} or {@code application.properties}.
 * Manages secret key strings and expiration times for both access and refresh token.
 *
 * <hr>
 *
 * JWT 관련 설정 정보를 담는 프로퍼티 클래스.
 *
 * <p>
 * {@code application.yml} 또는 {@code application.properties}의 {@code jwt} 접두사를 가진 값을 매핑합니다.
 * access token 및 refresh token의 비밀키 문자열 및 만료 시간을 관리합니다.
 *
 * <hr>
 *
 * <p>
 * Example YAML:
 * <pre>
 * jwt:
 *   access:
 *     secret: access-secret-must-be-at-least-32-characters-long
 *     expires: 3600000
 *   refresh:
 *     secret: refresh-secret-must-be-at-least-32-characters-long
 *     expires: 86400000
 * </pre>
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @since 1.0.0
 */
@Slf4j
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Properties for the access token <hr>
     * access token을 위한 프로퍼티
     */
    private final KeyProperties access;

    /**
     * Properties for the refresh token <hr>
     * refresh token을 위한 프로퍼티
     */
    private final KeyProperties refresh;

    /**
     * Constructs a new {@code JwtProperties} instance with the provided access and refresh token properties.
     *
     * <hr>
     *
     * 지정된 access 및 refresh 프로퍼티로 {@code JwtProperties} 인스턴스를 생성합니다.
     *
     * <hr>
     *
     * @param access    the access token properties
     * @param refresh   the refresh token properties
     */
    public JwtProperties(KeyProperties access, KeyProperties refresh) {
        this.access = access;
        this.refresh = refresh;
        log.debug("JwtProperties initialized with token properties below");
        log.debug("Access Properties (secret: {}, expires: {}", access.getSecret(), access.getExpires());
        log.debug("Refresh Properties (secret: {}, expires: {})", refresh.getSecret(), refresh.getExpires());
    }

    /**
     * Returns the access token properties. <hr>
     *
     * access token 프로퍼티를 반환합니다. <hr>
     *
     * @return the access token properties
     */
    public KeyProperties getAccess() {
        return this.access;
    }

    /**
     * Returns the refresh token properties. <hr>
     *
     * refresh token 프로퍼티를 반환합니다. <hr>
     *
     * @return the refresh token properties
     */
    public KeyProperties getRefresh() {
        return this.refresh;
    }

    /**
     * Represents Key-related properties for a JWT token.
     *
     * <p>
     * Holds the secret key string and expiration time for a token.
     * The expiration time is expressed in milliseconds.
     *
     * <p>
     * This class is used as a nested property within {@code JwtProperties}
     *
     * <hr>
     *
     * Key 관련 설정 정보를 담는 프로퍼티 클래스
     *
     * <p>
     * token에 대한 비밀키 문자열 및 만료 시간을 관리합니다.
     * 만료 시간은 밀리초 단위로 표현됩니다.
     *
     * <p>
     * 이 클래스는 {@code JwtProperties}의 중첩 프로퍼티로 사용됩니다.
     *
     * <hr>
     *
     * @author Wooseong Urm
     * @since 1.0.0
     *
     */
    public static class KeyProperties {

        /**
         * Secret key string <hr>
         * 비밀키 문자열
         */
        private final String secret;

        /**
         * Expiration time in milliseconds. <hr>
         * 밀리초 단위 만료 시간
         */
        private final long expires; // milliseconds

        /**
         * Constructs a new {@code KeyProperties} instance with the provided secret key string and expiration time.
         *
         * <hr>
         *
         * 지정된 비밀키 문자열 및 만료 시간으로 {@code KeyProperties} 인스턴스를 생성합니다.
         *
         * <hr>
         *
         * @param secret    the secret key string
         * @param expires   the expiration time in milliseconds
         */
        public KeyProperties(String secret, long expires) {
            this.secret = secret;
            this.expires = expires;
        }

        /**
         * Returns the secret key string. <hr>
         *
         * 비밀키 문자열을 반환합니다. <hr>
         *
         * @return the secret key string
         */
        public String getSecret() {
            return this.secret;
        }

        /**
         * Returns the expiration time in milliseconds. <hr>
         *
         * 밀리초 단위 만료 시간을 반환합니다. <hr>
         *
         * @return the expiration time in milliseconds.
         */
        public long getExpires() {
            return this.expires;
        }

    }
}
