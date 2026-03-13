package com.inspire.common.jwt.config;

import com.inspire.common.jwt.JwtUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto-configuration for the {@link JwtUtils}.
 *
 * <p>
 * This configuration registers a {@link JwtUtils} bean when no other
 * {@link JwtUtils} bean is already defined in the application context.
 *
 * <hr>
 *
 * {@link JwtUtils}를 위한 자동 환경 설정.
 *
 * <p>
 * {@link JwtUtils} 빈이 애플리케이션 컨텍스트에 등록되지 않았을 경우, 등록합니다.
 *
 * <hr>
 *
 * @author Wooseong Urm
 * @since 1.0.0
 */
@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

    /**
     * Creates a {@link JwtUtils} bean if no bean is present.
     *
     * <hr>
     *
     * {@link JwtUtils} bean이 등록되지 않은 경우 생성합니다.
     *
     * <hr>
     *
     * @param jwtProperties the JWT configuration properties
     * @return a {@link JwtUtils} instance
     * @see JwtUtils
     * @see JwtProperties
     */
    @Bean
    @ConditionalOnMissingBean(JwtUtils.class)
    public JwtUtils jwtUtils(JwtProperties jwtProperties) {
        return new JwtUtils(jwtProperties);
    }
}
