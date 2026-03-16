package com.inspire.common.cookie.model;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;

/**
 * Represents the attributes of an HTTP cookie.
 *
 * <p>
 * This class provides a structured way to define cookie properties such as
 * name, value, domain, path, expiration, security flags, and same-site policy.
 * In addition, it ensures validation of the {@code sameSite}.
 *
 * <hr>
 *
 * HTTP 쿠키의 속성을 나타내기 위한 클래스.
 *
 * <p>
 * 쿠키의 이름, 값, 도메인, 경로, 만료, 보안 플래그, same-site 정책 등 쿠키의 속성을
 * 정의하기 위한 클래스입니다.
 * 추가로 {@code sameSite}의 값을 검증합니다.
 *
 * <hr>
 *
 * <p>
 * Typical usage:
 * <pre>{@code
 * CookieAttributes cookie = CookieAttributes.builder()
 *     .name("SESSIONID")
 *     .value("abc123")
 *     .domain("example.com") // .domain(".example.com") if you would like to follow the legacy formatting.
 *     .path("/")
 *     .httpOnly(true)
 *     .secure(true)
 *     .sameSite("Strict")
 *     .maxAge(3600)
 *     .build();
 *
 * System.out.println(cookie.toString());
 * }
 * </pre>
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @since 1.0.0
 */
@Slf4j
public class CookieAttributes {
    private final String name;
    private final String value;
    private final String domain;
    private final String path;
    private final int maxAge;
    private final boolean secure;
    private final boolean httpOnly;
    private final String sameSite;
    private static final Set<String> VALID_SAME_SITES = Set.of("strict", "lax", "none");

    /**
     * Constructs a new {@code CookieAttributes} instance with the provided properties.
     *
     * <hr>
     *
     * 주어진 속성을 기반으로 {@code CookieAttributes} 인스턴스를 생성합니다.
     *
     * <hr>
     *
     * @param name      the name of the cookie; must not be {@code null}
     * @param value     the value of the cookie; defaults to an empty string if {@code null}
     * @param maxAge    the maximum age in seconds; defaults to {@code -1} (session cookie) if {@code null}
     * @param domain    the domain of the cookie
     * @param path      the path of the cookie
     * @param httpOnly  whether the cookie is HTTP-only
     * @param secure    whether the cookie is secure
     * @param sameSite  the same-site policy; must be {@code Strict}, {@code Lax}, or {@code None} (case-insensitive), or {@code null}
     * @throws IllegalArgumentException if {@code name} is null or {@code sameSite} is invalid
     */
    @Builder
    public CookieAttributes(String name,
                            String value,
                            Integer maxAge,
                            String domain,
                            String path,
                            Boolean httpOnly,
                            Boolean secure,
                            String sameSite) {

        Assert.notNull(name, "Name must not be null");
        Assert.isTrue(validateSameSite(sameSite), "SameSite must be one of Strict, Lax, or None, case-insensitively. Found: " + sameSite);

        this.name = name;
        this.value = value != null ? value : "";
        this.maxAge = maxAge != null ? maxAge : -1; // default: -1, session cookie. (세션 쿠키)
        this.domain = domain; // if null, it delegates to a servlet. (null인 경우, 서블릿에 위임)
        this.path = path; // if null, it delegates to a servlet. (null인 경우, 서블릿에 위임)
        this.httpOnly = httpOnly != null ? httpOnly : false; // default: false, can be accessed through JavaScript. (JavaScript 접근 가능)
        this.secure = secure != null ? secure : false; // default: false, can be sent through HTTP (HTTP로 전송)
        this.sameSite = capitalizeSameSite(sameSite); // if null, it delegates to a browser. in most cases, Lax. (null인 경우, 브라우저에 위임. 대부분의 경우 Lax)

        log.debug("CookieAttributes created with (name: {}, maxAge: {}, domain: {}, path: {}, httpOnly: {}, secure: {}, sameSite: {})",
                this.name,
                this.maxAge,
                this.domain,
                this.path,
                this.httpOnly,
                this.secure,
                this.sameSite
        );
    }

    /**
     * Capitalizes the {@code sameSite} attribute value.
     *
     * <p>
     * Ensures the fist character is uppercase and the rest are lowercase.
     * If {@code sameSite} is {@code null}, returns {@code null}.
     *
     * <hr>
     *
     * {@code sameSite} 값의 첫 문자르 대문자로 치환합니다.
     *
     * <p>
     * 첫 문자는 대문자로, 나머지 문자는 소문자가 되도록 합니다. 만약 {@code sameSite}가 {@code null}이라면,
     * {@code null}을 반환합니다.
     *
     * <hr>
     *
     * @param sameSite the same-site policy
     * @return the capitalized sameSite value, or {@code null} if input is {@code null}
     */
    // This method must be used after the validation. (유효성 검증 이후에 사용)
    private String capitalizeSameSite(String sameSite) {

        String capitalizedSameSite = sameSite == null ? null : sameSite.substring(0, 1).toUpperCase() + sameSite.substring(1).toLowerCase();
        log.trace("Capitalized sameSie string: {} -> {}", sameSite, capitalizedSameSite);

        return capitalizedSameSite;
    }

    /**
     * Validates the {@code sameSite} attribute.
     *
     * <p>
     * For cookies, the {@code sameSite} attribute value must be {@code Lax}, {@code Strict}, or {@code None}.
     * This method validates that the provided {@code sameSite} can be capitalized or delegated to a browser.
     *
     * <hr>
     *
     * {@code sameSite} 속성값을 검증합니다.
     *
     * <p>
     * 쿠키의 {@code sameSite} 값은 반드시 {@code Lax}, {@code Strict}, {@code None} 중 하나여야 합니다.
     * 이 메소드는 주어진 {@code sameSite} 값의 대소문자 변환 후 브라우저에 의해 올바르게 해석될 수 있는지 검증합니다.
     *
     * <hr>
     *
     * @param sameSite the SameSite attribute value to validate
     * @return {@code true} if valid, {@code false} otherwise
     */
    private boolean validateSameSite(String sameSite) {

        return sameSite == null || VALID_SAME_SITES.contains(sameSite.toLowerCase());
    }

    /**
     * Returns a string representation of the cookie in the standard HTTP format.
     *
     * <hr>
     *
     * 표준 HTTP 형식에 맞는 쿠키 문자열을 반환합니다
     *
     * <hr>
     *
     * @return the formatted cookie string
     */
    @Override
    public String toString() {
        StringBuilder cookieString = new StringBuilder();
        cookieString.append(this.name).append("=").append(this.value);
        if (this.domain != null) cookieString.append("; Domain=").append(this.domain);
        if (this.path != null) cookieString.append("; Path=").append(this.path);
        if (this.maxAge >= 0) cookieString.append("; Max-Age=").append(this.maxAge);
        if (this.secure) cookieString.append("; Secure");
        if (this.httpOnly) cookieString.append("; HttpOnly");
        if (this.sameSite != null) cookieString.append("; SameSite=").append(this.sameSite);

        String formattedCookie = cookieString.toString();
        log.trace("Formatted cookie string: {}", formattedCookie);

        return formattedCookie;
    }
}
