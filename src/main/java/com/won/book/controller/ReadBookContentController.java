package com.won.book.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/read/content")
@Api(tags = "3. Read Book content Controller")
public class ReadBookContentController {

    @GetMapping
    @ApiOperation(value = "읽은 책 글귀 리스트")
    public String getList(String str) {
        return "Hello, " + str;
    }

    @PostMapping
    @ApiOperation(value = "읽은 책 글귀 저장 하기")
    public String save(String str, String str2) {
        return "Hello, " + str;
    }

    @PatchMapping("/{content-id}")
    @ApiOperation(value = "읽은 책 글귀 수정 하기")
    public String update(@PathVariable("content-id") Long contentId, String str) {
        return "Hello, " + str;
    }

    @DeleteMapping("/{content-id}")
    @ApiOperation(value = "읽은 책 글귀 삭제 하기")
    public String delete(@PathVariable("content-id") Long contentId, String str) {
        return "Hello, " + str;
    }
}
