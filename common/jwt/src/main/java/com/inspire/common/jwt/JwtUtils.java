package com.inspire.common.jwt;

import com.inspire.common.jwt.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

/**
 * Utility class for creating, parsing, and validating JWT tokens.
 *
 * <p>
 * Provides methods to create and validate JWT tokens, and parse JWT claims.
 *
 * <hr>
 *
 * JWT token의 생성, 파싱, 검증을 위한 유틸리티 클래스.
 *
 * <p>
 * JWT token의 생성 및 검증, JWT claim 파싱을 위한 기능을 제공합니다.
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @since 1.0.0
 */
@Slf4j
public class JwtUtils {

    /**
     * Signing key used to generate and verify access tokens. <hr>
     *
     * access token 발급과 검증에 사용되는 서명키.
     */
    private final Key accessKey;

    /**
     * Signing key used to generate and verify refresh tokens. <hr>
     *
     * refresh token 발급과 검증에 사용되는 서명키.
     */
    private final Key refreshKey;

    /**
     * Expiration time for access tokens in milliseconds. <hr>
     *
     * 밀리초 단위 access token 만료 시간.
     */
    private final long accessExpires; // milliseconds

    /**
     * Expiration time for refresh tokens in milliseconds. <hr>
     *
     * 밀리초 단위 refresh token 만료 시간.
     */
    private final long refreshExpires; // milliseconds

    /**
     * Constructs a new {@code JwtUtils} instance with the provided secret key strings and expiration times.
     *
     * <p>
     * Validates inputs and initializes signing keys for access and refresh tokens.
     *
     * <hr>
     *
     * 지정된 시크릿 키 문자열과 만료 시간으로 {@code JwtUtils} 인스턴스를 생성합니다.
     *
     * <p>
     * 입력값을 검증하고, access token과 refresh token의 서명키를 초기화합니다.
     *
     * <hr>
     *
     * @param accessSecret      the secret key string for signing access tokens
     * @param refreshSecret     the secret key string for signing refresh tokens
     * @param accessExpires     the expiration time of access tokens in milliseconds
     * @param refreshExpires    the expiration time of refresh tokens in milliseconds
     */
    public JwtUtils(String accessSecret, String refreshSecret, long accessExpires, long refreshExpires) {
        Assert.hasText(accessSecret, "jwt.access.secret must be set to a non-null string value.");
        Assert.hasText(refreshSecret, "jwt.refresh.secret must be set to a non-null string value.");
        Assert.isTrue(accessExpires > 0, "jwt.access.expires must be set to an integer greater than 0");
        Assert.isTrue(refreshExpires > 0, "jwt.refresh.expires must be set to an integer greater than 0");

        this.accessKey = Keys.hmacShaKeyFor(accessSecret.getBytes(StandardCharsets.UTF_8));
        this.refreshKey = Keys.hmacShaKeyFor(refreshSecret.getBytes(StandardCharsets.UTF_8));
        this.accessExpires = accessExpires;
        this.refreshExpires = refreshExpires;

        log.debug("JwtUtils initialized with (accessExpires: {}ms, refreshExpires: {}ms)", accessExpires, refreshExpires);
    }

    /**
     * Constructs a new {@code JwtUtils} instance using the provided {@link JwtProperties}.
     *
     * <p>
     * Internally extracts the secrets and expiration times for both access and refresh tokens
     * and delegates to another constructor.
     *
     * <hr>
     *
     * 주어진 {@link JwtProperties}를 이용하여 {@code JwtUtils} 인스턴스를 생성합니다.
     *
     * <p>
     * 내부적으로 access token과 refresh token의 시크릿 키 문자열 및 만료 시간을 다른 생성자로 전달합니다.
     *
     * <hr>
     *
     * @param jwtProperties the JWT configuration properties
     * @see JwtProperties
     * @see #JwtUtils(String, String, long, long)
     */
    public JwtUtils(JwtProperties jwtProperties) {
        this(jwtProperties.getAccess().getSecret(),
                jwtProperties.getRefresh().getSecret(),
                jwtProperties.getAccess().getExpires(),
                jwtProperties.getRefresh().getExpires());
    }

    /**
     * Creates a new access token for a user with roles and issued timestamp.
     *
     * <p>
     * Generates a signed JWT access token including user ID as a subject and roles.
     *
     * <hr>
     *
     * 유저의 권한과 발급 시간을 기반으로 access token을 생성합니다.
     *
     * <p>
     * 사용자 ID를 subject로 사용하고, 권한을 포함하여 서명된 JWT access token을 생성합니다.
     *
     * <hr>
     *
     * @param userId    the user ID
     * @param roles     the roles assigned to the user
     * @param issuedAt  the issued timestamp in milliseconds
     * @return the signed JWT access token
     */
    public String createAccessToken(Long userId, Collection<String> roles, long issuedAt) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notEmpty(roles, "roles must not be null or empty.");

        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("roles", roles)
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(new Date(issuedAt + accessExpires))
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();

        log.debug("Access token generated for (user: {})", userId);
        log.debug("Access token: {}", token);

        return token;
    }

    /**
     * Creates a new access token for a user using the current time.
     *
     * <hr>
     *
     * 현재 시각을 발급 시간으로 하여 access token을 생성합니다.
     *
     * <hr>
     *
     * @param userId    the user ID
     * @param roles     the roles assigned to the user
     * @return the signed JWT access token
     * @see #createAccessToken(Long, Collection, long)
     */
    public String createAccessToken(Long userId, Collection<String> roles) {
        long now = System.currentTimeMillis();
        return createAccessToken(userId, roles, now);
    }

    /**
     * Creates a new refresh token for a user with issued timestamp.
     *
     * <p>
     * Generates a signed JWT refresh token including user ID as a subject.
     *
     * <hr>
     *
     * 발급 시간을 기반으로 refresh token을 생성합니다.
     *
     * <p>
     * 사용자 ID를 subject로 사용하여 서명된 JWT refresh token을 생성합니다.
     *
     * <hr>
     *
     * @param userId    the user ID
     * @param issuedAt  the issued timestamp in milliseconds
     * @return the signed JWT refresh token
     */
    public String createRefreshToken(Long userId, long issuedAt) {
        Assert.notNull(userId, "userId must not be null.");

        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(issuedAt))
                .setExpiration(new Date(issuedAt + refreshExpires))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();

        log.debug("Refresh token generated for (user: {})", userId);
        log.debug("Refresh token: {}", token);

        return token;
    }

    /**
     * Creates a new refresh token for a user with the current timestamp.
     *
     * <hr>
     *
     * 현재 시각을 발급 시간으로 하여 refresh token을 생성합니다.
     *
     * <hr>
     *
     * @param userId the user ID
     * @return the signed JWT refresh token
     * @see #createRefreshToken(Long, long)
     */
    public String createRefreshToken(Long userId) {
        long now = System.currentTimeMillis();
        return createRefreshToken(userId, now);
    }

    /**
     * Parses a JWT token using the provided signing key.
     *
     * <hr>
     *
     * 주어진 서명키로 JWT token을 파싱합니다.
     *
     * <hr>
     *
     * @param token         the JWT token string
     * @param signingKey    the key used to verify the signature
     * @return parsed claims
     * @throws JwtValidationException if the token is invalid or expired
     */
    private Claims parseToken(String token, Key signingKey) {
        Assert.hasText(token, "token must not be empty");
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            log.debug("JWT parsed successfully for (user: {})", claims.getSubject());
        } catch (ExpiredJwtException e) {
            log.debug("JWT expired");
            throw new JwtValidationException("JWT expired", e);
        } catch (SignatureException e) {
            log.debug("Invalid JWT signature");
            throw new JwtValidationException("Invalid JWT signature", e);
        } catch (JwtException e) {
            log.debug("Unsupported or Malformed JWT accepted");
            throw new JwtValidationException("Unsupported or Malformed JWT accepted", e);
        }
        return claims;
    }

    /**
     * Parses an access token using the configured access key.
     *
     * <hr>
     *
     * 설정된 서명키로 access token을 파싱합니다.
     *
     * <hr>
     *
     * @param token the JWT access token
     * @return parsed claims
     * @throws JwtValidationException if the token is invalid or expired
     * @see #parseToken(String, Key)
     */
    public Claims parseAccessToken(String token) {
        return parseToken(token, accessKey);
    }

    /**
     * Parses a refresh token using the configured refresh key.
     *
     * <hr>
     *
     * 설정된 서명키로 refresh token을 파싱합니다.
     *
     * <hr>
     *
     * @param token the JWT refresh token
     * @return parsed claims
     *         (파싱된 클레임)
     * @throws JwtValidationException if the token is invalid or expired
     *                                (token이 만료되거나 유효하지 않은 경우)
     * @see #parseToken(String, Key)
     */
    public Claims parseRefreshToken(String token) {
        return parseToken(token, refreshKey);
    }

    /**
     * Validates a JWT token using the provided signing key.
     *
     * <hr>
     *
     * 주어진 서명키로 JWT token의 유효성을 검증합니다.
     *
     * <hr>
     *
     * @param token         the JWT token string
     * @param signingKey    the key used to verify the signature
     * @return {@code true} if valid, {@code false} otherwise
     * @see #parseToken(String, Key)
     */
    private boolean validateToken(String token, Key signingKey) {
        try {
            parseToken(token, signingKey);
            return true;
        } catch (JwtValidationException e) {
            return false;
        }
    }

    /**
     * Validates an access token using the configured access key.
     *
     * <hr>
     *
     * 설정된 서명키로 access token의 유효성을 검증합니다.
     *
     * <hr>
     *
     * @param token the JWT access token
     * @return {@code true} if valid, {@code false} otherwise
     * @see #validateToken(String, Key)
     */
    public boolean validateAccessToken(String token) {
        return validateToken(token, accessKey);
    }

    /**
     * Validates a refresh token using the configured refresh key.
     *
     * <hr>
     *
     * 설정된 서명키로 refresh token의 유효성을 검증합니다.
     *
     * <hr>
     *
     * @param token the JWT refresh token
     * @return {@code true} if valid, {@code false} otherwise
     * @see #validateToken(String, Key)
     */
    public boolean validateRefreshToken(String token) {
        return validateToken(token, refreshKey);
    }

    /**
     * Returns the configured expiration time of access tokens in seconds.
     *
     * <hr>
     *
     * 설정된 access token의 만료 기한을 초 단위로 반환합니다.
     *
     * <hr>
     *
     * @return the expiration time of access tokens in seconds.
     */
    public Long getAccessExpiresInSeconds() {
        return accessExpires / 1000;
    }

    /**
     * Extracts the user ID from a JWT token string using the provided signing key.
     *
     * <hr>
     *
     * 주어진 서명키로 JWT token 내부 사용자 ID를 추출합니다.
     *
     * <hr>
     *
     * @param token         the JWT token string
     * @param signingKey    the key used to verify the signature
     * @return the user ID
     * @throws JwtValidationException if the token is invalid or expired
     * @see #parseToken(String, Key)
     */
    private Long getUserId(String token, Key signingKey) {
        return Long.parseLong(parseToken(token, signingKey).getSubject());
    }

    /**
     * Extracts the user ID from an access token.
     *
     * <hr>
     *
     * access token 내부 사용자 ID를 추출합니다.
     *
     * <hr>
     *
     * @param token the JWT access token
     * @return the user ID
     * @throws JwtValidationException if the token is invalid or expired
     * @see #getUserId(String, Key)
     */
    public Long getUserIdFromAccessToken(String token) {
        return getUserId(token, accessKey);
    }

    /**
     * Extracts the user ID from a refresh token.
     *
     * <hr>
     *
     * refresh token 내부 사용자 ID를 추출합니다.
     *
     * <hr>
     *
     * @param token the JWT refresh token
     * @return the user ID
     * @throws JwtValidationException if the token is invalid or expired
     * @see #getUserId(String, Key)
     */
    public Long getUserIdFromRefreshToken(String token) {
        return getUserId(token, refreshKey);
    }

    /**
     * Retrieves the roles assigned to the user from an access token.
     *
     * <hr>
     *
     * access token 내부 권한 목록을 불러옵니다.
     *
     * <hr>
     *
     * @param token the JWT access token
     * @return a list of role names
     * @throws JwtValidationException if the token is invalid or expired
     * @see #parseToken(String, Key)
     */
    public List<String> getRoles(String token) {
        return ((List<?>) parseAccessToken(token).get("roles"))
                .stream()
                .map(Object::toString)
                .toList();
    }
}
