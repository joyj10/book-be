package com.won.book.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/want")
@Api(tags = "2. Want Book Controller")
public class WantBookController {

    @GetMapping
    @ApiOperation(value = "읽고 싶은 책 리스트")
    public String getList(String str) {
        return "Hello, " + str;
    }

    @GetMapping("/{want-book-id}")
    @ApiOperation(value = "읽고 싶은 책 상세 보기")
    public String getDetail(@PathVariable("want-book-id") Long wantBookId, String str) {
        return "Hello, " + str;
    }

    @PostMapping
    @ApiOperation(value = "읽고 싶은 책 저장 하기")
    public String save(String str) {
        return "Hello, " + str;
    }

    @PatchMapping("/{want-book-id}")
    @ApiOperation(value = "읽고 싶은 책 수정 하기")
    public String update(@PathVariable("want-book-id") Long wantBookId, String str) {
        return "Hello, " + str;
    }

    @DeleteMapping("/{want-book-id}")
    @ApiOperation(value = "읽고 싶은 책 삭제 하기")
    public String delete(@PathVariable("want-book-id") Long wantBookId, String str) {
        return "Hello, " + str;
    }
}
