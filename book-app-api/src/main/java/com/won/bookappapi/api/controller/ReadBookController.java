package com.won.bookappapi.api.controller;

import com.won.bookcommon.response.ResponseResult;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookappapi.service.ReadBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "1. Read Book Controller")
public class ReadBookController {

    private final ReadBookService readBookService;

    @GetMapping("/v1/book/read")
    @ApiOperation(value = "읽은 책 리스트")
    public ResponseResult<List<ReadBookDto>> getList() {
        // token 멤버 데이터 추출
        Long memberId = 1L;
        // 읽은 책 리스트 조회
        /** [추가작업] 페이징 처리 필요 */
        /** [추가작업] 검색 기능 필요 */
        /** [추가작업] 정렬 기능 필요 */
        return new ResponseResult<>(readBookService.getList(memberId));
    }

    @GetMapping("/v1/book/read/{read-book-id}")
    @ApiOperation(value = "읽은 책 상세")
    public ResponseResult<ReadBookDto> getDetail(@PathVariable("read-book-id") Long readBookId) {
        // token 멤버 데이터 추출
        Long memberId = 1L;
        return new ResponseResult<>(readBookService.getDetail(memberId, readBookId));
    }

    @PostMapping("/v1/book/read")
    @ApiOperation(value = "읽은 책 저장")
    public String save(String str, String str2) {
        return "Hello, " + str;
    }

    @PatchMapping("/v1/book/read/{read-book-id}")
    @ApiOperation(value = "읽은 책 수정")
    public String update(@PathVariable("read-book-id") Long readBookId, String str) {
        return "Hello, " + str;
    }

    @DeleteMapping("/v1/book/read/{read-book-id}")
    @ApiOperation(value = "읽은 책 삭제")
    public String delete(@PathVariable("read-book-id") Long readBookId, String str) {
        return "Hello, " + str;
    }

    @PostMapping("/v1/book/read/repeat")
    @ApiOperation(value = "다시 읽은 책 저장")
    public String saveRepeat(String str, String str2) {
        return "Hello, " + str;
    }
}
