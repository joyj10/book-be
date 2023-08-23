package com.won.book.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/read")
@Api(tags = "1. Read Book Controller")
public class ReadBookController {

    @GetMapping
    @ApiOperation(value = "읽은 책 리스트")
    public String getList(String str) {
        return "Hello, " + str;
    }

    @GetMapping("/{read-book-id}")
    @ApiOperation(value = "읽은 책 상세 보기")
    public String getDetail(@PathVariable("read-book-id") Long readBookId, String str) {
        return "Hello, " + str;
    }

    @PostMapping
    @ApiOperation(value = "읽은 책 저장 하기")
    public String save(String str, String str2) {
        return "Hello, " + str;
    }

    @PatchMapping("/{read-book-id}")
    @ApiOperation(value = "읽은 책 수정 하기")
    public String update(@PathVariable("read-book-id") Long readBookId, String str) {
        return "Hello, " + str;
    }

    @DeleteMapping("/{read-book-id}")
    @ApiOperation(value = "읽은 책 삭제 하기")
    public String delete(@PathVariable("read-book-id") Long readBookId, String str) {
        return "Hello, " + str;
    }
}
