package com.won.book.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "테스트 컨트롤러")
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "test")
    public String test(String str) {
        return "Hello, " + str;
    }
}
