package com.won.bookappapi.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Text Controller", description = "테스트 컨트롤러")
public class TestController {

    @GetMapping("/test")
    public String test(String str) {
        return "Hello, " + str;
    }

    @GetMapping("/ex")
    @Operation(summary = "예외 테스트", description = "")
    public String testEx(String str) {
        if ("i".equals(str)) {
            throw new IllegalArgumentException("error");
        } else if ("500".equals(str)) {
            throw new RuntimeException();
        }
        return "Hello, " + str;
    }
}
