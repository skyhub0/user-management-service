package com.inspire.common.contract.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDTO {
    private String message;

    public TestDTO(String message) {
        this.message = message;
    }
}