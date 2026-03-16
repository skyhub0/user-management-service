package com.inspire.common.cookie.autoconfigure;

import com.inspire.common.cookie.config.CookieProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Auto-configuration for the {@code CookieUtils}.
 *
 * <p>
 * This configuration registers a {@code CookieUtils} bean when no other
 * {@code CookieUtils} bean is already defined in the application context.
 *
 * <hr>
 *
 * {@code CookieUtils}를 위한 자동 환경 설정.
 *
 * <p>
 * {@code CookieUtils} 빈이 애플리케이션 컨텍스트에 등록되지 않았을 경우, 등록합니다.
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @see com.inspire.common.cookie.servlet.CookieUtils CookieUtils for MVC
 * @see com.inspire.common.cookie.reactive.CookieUtils CookieUtils for Reactive
 * @since 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(CookieProperties.class)
public class CookieAutoConfiguration {

    /**
     * Creates a {@link com.inspire.common.cookie.servlet.CookieUtils CookieUtils} bean if no bean is present.
     *
     * <hr>
     *
     * {@link com.inspire.common.cookie.servlet.CookieUtils CookieUtils} bean이 등록되지 않은 경우 생성합니다.
     *
     * <hr>
     *
     * @param cookieProperties the cookie configuration properties
     * @return a {@link com.inspire.common.cookie.servlet.CookieUtils CookieUtils} instance
     * @see CookieProperties
     */
    @Bean(name = "cookieUtils")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnMissingBean(name = "cookieUtils")
    public com.inspire.common.cookie.servlet.CookieUtils cookieUtilsServlet(CookieProperties cookieProperties) {
        return new com.inspire.common.cookie.servlet.CookieUtils(cookieProperties);
    }

    /**
     * Creates a {@link com.inspire.common.cookie.reactive.CookieUtils CookieUtils} bean if no bean is present.
     *
     * <hr>
     *
     * {@link com.inspire.common.cookie.reactive.CookieUtils CookieUtils} bean이 등록되지 않은 경우 생성합니다.
     *
     * <hr>
     *
     * @param cookieProperties the cookie configuration properties
     * @return a {@link com.inspire.common.cookie.reactive.CookieUtils CookieUtils} instance
     * @see CookieProperties
     */
    @Bean(name = "cookieUtils")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @ConditionalOnMissingBean(name = "cookieUtils")
    public com.inspire.common.cookie.reactive.CookieUtils cookieUtils(CookieProperties cookieProperties) {
        return new com.inspire.common.cookie.reactive.CookieUtils(cookieProperties);
    }
}
