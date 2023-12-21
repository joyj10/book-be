package com.won.bookappapi.api.controller;

import com.won.bookcommon.response.ResponseResult;
import com.won.bookappapi.service.dto.WantBookDto;
import com.won.bookappapi.service.WantBookService;
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
    public ResponseResult<List<WantBookDto>> getList() {
        // TODO jwt 토큰 처리
        Long memberId = 1L;
        List<WantBookDto> result = wantBookService.getList(memberId);
        return new ResponseResult<>(result);
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
