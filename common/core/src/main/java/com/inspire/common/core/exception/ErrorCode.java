package com.inspire.common.core.exception;

/**
 * 각 micro service에서의 error를 담당하기 위한 interface
 * enum에서 implements 할 것
 */
public interface ErrorCode {
    /**
     *
     * @return
     */
    int getStatus();

    /**
     *
     * @return
     */
    String getCode();

    /**
     *
     * @return
     */
    String getMessage();
}
