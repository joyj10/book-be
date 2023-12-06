package com.won.book.api.controller;

import com.won.book.common.response.ResultResponse;
import com.won.book.domain.wantbook.dto.WantBookDto;
import com.won.book.domain.wantbook.service.WantBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/want")
@Api(tags = "2. Want Book Controller")
@RequiredArgsConstructor
public class WantBookController {

    private final WantBookService wantBookService;

    @GetMapping
    @ApiOperation(value = "읽고 싶은 책 리스트")
    public ResultResponse<List<WantBookDto>> getList() {
        // TODO jwt 토큰 처리
        Long memberId = 1L;
        List<WantBookDto> result = wantBookService.getList(memberId);
        return new ResultResponse<>(result);
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
