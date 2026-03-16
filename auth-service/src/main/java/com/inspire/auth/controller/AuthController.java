package com.inspire.auth.controller;

import com.inspire.auth.dto.TestDTO;
import com.inspire.auth.exception.AuthErrorCode;
import com.inspire.auth.exception.AuthException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/error")
    public String error() {
        throw new AuthException(AuthErrorCode.TEST);
    }

    @GetMapping("/test2")
    public String test2(@Valid @ModelAttribute TestDTO testDTO) {
        return "test2";
    }

    public ResponseEntity<Void> test3() {
        return null;
    }
}
