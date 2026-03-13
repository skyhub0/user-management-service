package com.inspire.common.contract.handler;

import com.inspire.common.contract.dto.ApiResponse;
import com.inspire.common.contract.exception.BaseException;
import com.inspire.common.contract.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

/**
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice implements ResponseBodyAdvice<Object> {

    /**
     *
     */
    private final ObjectMapper objectMapper;

    /**
     *
     */
    private static final Set<HttpMethod> EXCLUDED_METHODS = Set.of(HttpMethod.HEAD, HttpMethod.OPTIONS);

    /**
     *
     * @param objectMapper
     */
    public GlobalControllerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BaseException e) {

        ErrorCode errorCode = e.getErrorCode();
        int status = errorCode.getStatus();


        return ResponseEntity.status(status).body(ApiResponse.error(errorCode));
    }

    /**
     *
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        log.debug("supports method called");

        return true;
    }

    /**
     *
     * @param body the body to be written
     * @param returnType the return type of the controller method
     * @param selectedContentType the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request the current request
     * @param response the current response
     * @return
     */
    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("beforeBodyWrite executed");
        log.info("content: {}", selectedContentType);
        log.info("converter: {}", selectedConverterType);

        Class<?> innerType = body != null ? body.getClass() : Object.class;
        log.debug("innerType: {}", innerType);

        // 이미 ApiResponse면 그대로
        if (body instanceof ApiResponse) {
            return body;
        }

        // HEAD, OPTIONS의 경우 그대로
        HttpMethod httpMethod = request.getMethod();
        if (EXCLUDED_METHODS.contains(httpMethod)) {
            return body;
        }

        // Check Content-Type if needed

        // Servlet Response만 처리
        if (!(response instanceof ServletServerHttpResponse ssr)) {
            return body;
        }

        int status = ssr.getServletResponse().getStatus();

        // status가 1xx, 204, 3xx의 경우 넘김
        if (isBodyLessStatus(status)) {
            return body;
        }

        ApiResponse<?> apiResponse = ApiResponse.success(status, body);

        if (StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            try {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return objectMapper.writeValueAsString(apiResponse);
            } catch (JacksonException e) {
                throw new RuntimeException(e);
            }
        }

        return apiResponse;
    }


    /**
     *
     * @param status
     * @return
     */
    private boolean isBodyLessStatus(int status) {
        return status / 100 == 1 || status / 100 == 3 || status == 204;
    }


}
