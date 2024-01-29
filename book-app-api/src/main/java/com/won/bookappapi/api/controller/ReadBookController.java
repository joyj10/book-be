package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.api.request.ReadBookUpdateRequest;
import com.won.bookappapi.api.request.YearMonthRequest;
import com.won.bookcommon.response.ResponseResult;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookappapi.service.ReadBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "1. Read Book Controller")
public class ReadBookController {

    private final ReadBookService readBookService;

    @GetMapping("/v1/book/read")
    @ApiOperation(value = "읽은 책 리스트")
    public ResponseResult<List<ReadBookDto>> getReadBooks() {
        // 읽은 책 리스트 조회
        /** [추가작업] 페이징 처리 필요 */
        /** [추가작업] 검색 기능 필요 */
        /** [추가작업] 정렬 기능 필요 */
        return new ResponseResult<>(readBookService.getReadBooks(getUserId()));
    }

    @GetMapping("/v1/book/read/{read-book-id}")
    @ApiOperation(value = "읽은 책 상세")
    public ResponseResult<ReadBookDto> getReadBook(@PathVariable("read-book-id") Long readBookId) {
        return new ResponseResult<>(readBookService.getReadBook(getUserId(), readBookId));
    }

    @PostMapping("/v1/book/read")
    @ApiOperation(value = "읽은 책 저장")
    public ResponseResult<Long> save(@Valid @RequestBody ReadBookCreateRequest request) {
        Long readBookId = readBookService.save(getUserId(), request);
        return new ResponseResult<>(readBookId);
    }

    @PatchMapping("/v1/book/read/{read-book-id}")
    @ApiOperation(value = "읽은 책 수정")
    public ResponseResult<Long> update(@PathVariable("read-book-id") Long readBookId, @Valid @RequestBody ReadBookUpdateRequest request) {
        Long id = readBookService.update(getUserId(), readBookId, request);
        return new ResponseResult<>(id);
    }

    @DeleteMapping("/v1/book/read/{read-book-id}")
    @ApiOperation(value = "읽은 책 삭제")
    public ResponseResult<Boolean> delete(@PathVariable("read-book-id") Long readBookId) throws BadRequestException {
        readBookService.delete(getUserId(), readBookId);
        return new ResponseResult<>(true);
    }

    @PostMapping("/v1/book/read/{read-book-id}/repeat")
    @ApiOperation(value = "다시 읽은 책 저장")
    public String saveRepeat(@PathVariable("read-book-id") Long readBookId, @Valid @RequestBody ReadBookCreateRequest request) {
        return "Hello" + readBookId;
    }

    @GetMapping("/v1/book/read/month")
    @ApiOperation(value = "월별 읽은 책 조회")
    public ResponseResult<List<ReadBookDto>> getReadBookOfMonth(@Valid @RequestParam YearMonthRequest yearMonthRequest) {
        return new ResponseResult<>(readBookService.getReadBookOfMonth(getUserId(), yearMonthRequest));
    }

    // 임시 처리 : JWT 작업 후 수정
    public Long getUserId() {
        return 1L;
    }
}
