package com.won.bookappapi.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "3. Read Book content Controller")
public class ReadBookContentController {

    @GetMapping("/vi/book/read/content")
    @ApiOperation(value = "읽은 책 글귀 리스트")
    public String getList(String str) {
        return "Hello, " + str;
    }

    @PostMapping("/vi/book/read/content")
    @ApiOperation(value = "읽은 책 글귀 저장 하기")
    public String save(String str, String str2) {
        return "Hello, " + str;
    }

    @PatchMapping("/vi/book/read/content/{content-id}")
    @ApiOperation(value = "읽은 책 글귀 수정 하기")
    public String update(@PathVariable("content-id") Long contentId, String str) {
        return "Hello, " + str;
    }

    @DeleteMapping("/vi/book/read/content/{content-id}")
    @ApiOperation(value = "읽은 책 글귀 삭제 하기")
    public String delete(@PathVariable("content-id") Long contentId, String str) {
        return "Hello, " + str;
    }
}
