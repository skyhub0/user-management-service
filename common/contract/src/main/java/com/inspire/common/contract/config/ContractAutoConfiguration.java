package com.inspire.common.contract.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspire.common.contract.handler.GlobalControllerAdvice;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(ObjectMapper.class)
public class ContractAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(GlobalControllerAdvice.class)
    public GlobalControllerAdvice globalControllerAdvice(ObjectMapper objectMapper) {
        return new GlobalControllerAdvice(objectMapper);
    }
}
