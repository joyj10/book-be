package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.WantBookCreateRequest;
import com.won.bookappapi.api.request.WantBookUpdateRequest;
import com.won.bookcommon.response.EmptyResult;
import com.won.bookcommon.response.ResponseResult;
import com.won.bookappapi.service.dto.WantBookDto;
import com.won.bookappapi.service.WantBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
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
        List<WantBookDto> result = wantBookService.getList(getUserId());
        return new ResponseResult<>(result);
    }

    @GetMapping("/{want-book-id}")
    @ApiOperation(value = "읽고 싶은 책 상세 보기")
    public ResponseResult<WantBookDto> getDetail(@PathVariable("want-book-id") Long wantBookId) {
        WantBookDto result = wantBookService.getDetail(getUserId(), wantBookId);
        return new ResponseResult<>(result);
    }

    @PostMapping
    @ApiOperation(value = "읽고 싶은 책 저장 하기")
    public ResponseResult<Long> save(@Valid @RequestBody WantBookCreateRequest createRequest) {
        Long result = wantBookService.save(getUserId(), createRequest);
        return new ResponseResult<>(result);
    }

    @PatchMapping("/{want-book-id}")
    @ApiOperation(value = "읽고 싶은 책 수정 하기")
    public ResponseResult<Long> update(@PathVariable("want-book-id") Long wantBookId,
                                       @Valid @RequestBody WantBookUpdateRequest updateRequest) {
        Long result = wantBookService.update(getUserId(), wantBookId, updateRequest);
        return new ResponseResult<>(result);
    }

    @DeleteMapping("/{want-book-id}")
    @ApiOperation(value = "읽고 싶은 책 삭제 하기")
    public ResponseResult<EmptyResult> delete(@PathVariable("want-book-id") Long wantBookId) {
        wantBookService.delete(wantBookId);
        return new ResponseResult<>(new EmptyResult());
    }

    // 임시 처리 : JWT 작업 후 수정
    public Long getUserId() {
        return 1L;
    }
}
