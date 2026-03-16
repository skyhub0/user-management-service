package com.inspire.common.contract.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire.common.contract.handler.GlobalControllerAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

/**
 *
 */
@AutoConfiguration(after = {JacksonAutoConfiguration.class, MessageSourceAutoConfiguration.class})
@ConditionalOnBean({ObjectMapper.class, MessageSource.class})
public class ContractAutoConfiguration {
    /**
     *
     * @param objectMapper
     * @return
     */
    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnMissingBean(GlobalControllerAdvice.class)
    public GlobalControllerAdvice globalControllerAdvice(ObjectMapper objectMapper) {
        return new GlobalControllerAdvice(objectMapper);
    }
}
