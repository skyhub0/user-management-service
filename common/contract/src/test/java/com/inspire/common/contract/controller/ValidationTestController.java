package com.inspire.common.contract.controller;

import com.inspire.common.contract.dto.ValidationTestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidationTestController {
    @GetMapping("/validation1")
    public String validation1(@Valid @RequestBody List<ValidationTestDTO> request) {
        System.out.println(request.toString());
        return "success";
    }

    @GetMapping("/validation2")
    public String validation2(@Valid @ModelAttribute ValidationTestDTO request) {
        System.out.println(request.toString());
        return "success";
    }
}
