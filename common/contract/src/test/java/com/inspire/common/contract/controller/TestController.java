package com.inspire.common.contract.controller;

import com.inspire.common.contract.dto.ApiResponse;
import com.inspire.common.contract.dto.TestDTO;
import com.inspire.common.contract.exception.TestErrorCode;
import com.inspire.common.contract.exception.TestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/method1")
    public ResponseEntity<TestDTO> method1() {
        return ResponseEntity.ok(new TestDTO("value1"));
    }

    @GetMapping("/method2")
    public TestDTO method2() {
        return new TestDTO("value2");
    }

    @GetMapping("/method3")
    public ResponseEntity method3() {
        return ResponseEntity.ok(new TestDTO("value3"));
    }

    @GetMapping("/method4")
    public ResponseEntity<TestDTO> method4() {
        return ResponseEntity.ok(new TestDTO(null));
    }

    @GetMapping("/method5")
    public TestDTO method5() {
        return new TestDTO(null);
    }

    @GetMapping("/method6")
    public ResponseEntity method6() {
        return ResponseEntity.ok(new TestDTO(null));
    }

    @GetMapping("/method7")
    public ResponseEntity<String> method7() {
        return ResponseEntity.ok("value4");
    }

    @GetMapping("/method8")
    public String method8() {
        return "value5";
    }

    @GetMapping("/method9")
    public ResponseEntity method9() {
        return ResponseEntity.ok("value6");
    }

    @GetMapping("/method10")
    public ResponseEntity<String> method10() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/method11")
    public String method11() {
        return null;
    }

    @GetMapping("/method12")
    public ResponseEntity method12() {
        return ResponseEntity.ok(null);
    }


    @GetMapping("/api1")
    public ResponseEntity<TestDTO> api1() {
        return ResponseEntity.status(204).body(null);
    }

    @GetMapping("/api2")
    public TestDTO api2() {
        return new TestDTO("api2");
    }

    @GetMapping("/api3")
    public ResponseEntity api3() {
        return ResponseEntity.status(208).body(new TestDTO("api3"));
    }

    @GetMapping("/api4")
    public ResponseEntity<ApiResponse<TestDTO>> api4() {
        return ResponseEntity.status(201).body(ApiResponse.success(201, new TestDTO("api4")));
    }

    @GetMapping("/api5")
    public ApiResponse<TestDTO> api5() {
        return ApiResponse.success(200, new TestDTO("api5"));
    }

    @GetMapping("/api6")
    public ResponseEntity api6() {
        return ResponseEntity.status(208).body(ApiResponse.success(208, new TestDTO("api6")));
    }

    @GetMapping("/api7")
    public ResponseEntity api7() {
        throw new TestException(TestErrorCode.TEST_ERROR1);
    }

    @GetMapping("/api8")
    public ResponseEntity<Void> api8() {
        throw new TestException(TestErrorCode.TEST_ERROR1);
    }

    @GetMapping("/api9")
    public void api9() {
        throw new TestException(TestErrorCode.TEST_ERROR1);
    }
    @GetMapping("/api10")
    public ResponseEntity<ApiResponse<TestDTO>> api10() {
        throw new TestException(TestErrorCode.TEST_ERROR1);
    }

    @GetMapping("/api11")
    public ApiResponse<TestDTO> api11() {
        throw new TestException(TestErrorCode.TEST_ERROR1);
    }

    @GetMapping("/api12")
    public TestDTO api12() {
        throw new TestException(TestErrorCode.TEST_ERROR1);
    }
}
