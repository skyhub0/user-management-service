package com.inspire.common.cookie.config;

import com.inspire.common.cookie.CookieUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration for the {@link CookieUtils}.
 *
 * <p>
 * This configuration registers a {@link CookieUtils} bean when no other
 * {@link CookieUtils} bean is already defined in the application context.
 *
 * <hr>
 *
 * {@link CookieUtils}를 위한 자동 환경 설정.
 *
 * <p>
 * {@link CookieUtils} 빈이 애플리케이션 컨텍스트에 등록되지 않았을 경우, 등록합니다.
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @since 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(CookieProperties.class)
public class CookieAutoConfiguration {

    /**
     * Creates a {@link CookieUtils} bean if no bean is present.
     *
     * <hr>
     *
     * {@link CookieUtils} bean이 등록되지 않은 경우 생성합니다.
     *
     * <hr>
     *
     * @param cookieProperties the cookie configuration properties
     * @return a {@link CookieUtils} instance
     * @see CookieUtils
     * @see CookieProperties
     */
    @Bean
    @ConditionalOnMissingBean(CookieUtils.class)
    public CookieUtils cookieUtils(CookieProperties cookieProperties) {
        return new CookieUtils(cookieProperties);
    }
}
