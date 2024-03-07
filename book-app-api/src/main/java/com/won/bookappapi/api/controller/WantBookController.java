package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.WantBookCreateRequest;
import com.won.bookappapi.api.request.WantBookUpdateRequest;
import com.won.bookappapi.service.WantBookService;
import com.won.bookappapi.service.dto.WantBookDto;
import com.won.bookcommon.response.EmptyResult;
import com.won.bookcommon.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Want Book Controller", description = "읽고 싶은 책 API")
public class WantBookController {

    private final WantBookService wantBookService;

    @GetMapping("/books/want")
    @Operation(summary = "읽고 싶은 책 리스트 조회", description = "읽고 싶은 책 리스트를 조회합니다.")
    public ResponseResult<List<WantBookDto>> getWantBooks() {
        List<WantBookDto> result = wantBookService.getWantBooks(getUserId());
        return new ResponseResult<>(result);
    }

    @GetMapping("/books/want/{want-book-id}")
    @Operation(summary = "읽고 싶은 책 상세 조회", description = "읽고 싶은 책의 상세 정보를 조회합니다.")
    public ResponseResult<WantBookDto> getWantBook(@PathVariable("want-book-id") Long wantBookId) {
        WantBookDto result = wantBookService.getWantBook(getUserId(), wantBookId);
        return new ResponseResult<>(result);
    }

    @PostMapping("/books/want")
    @Operation(summary = "읽고 싶은 책 저장", description = "읽고 싶은 책을 저장합니다.")
    public ResponseResult<Long> save(@Valid @RequestBody WantBookCreateRequest createRequest) {
        Long result = wantBookService.save(getUserId(), createRequest);
        return new ResponseResult<>(result);
    }

    @PatchMapping("/books/want/{want-book-id}")
    @Operation(summary = "특정 읽고 싶은 책 수정", description = "특정한 읽고 싶은 책의 정보를 수정합니다.")
    public ResponseResult<Long> update(@PathVariable("want-book-id") Long wantBookId,
                                       @Valid @RequestBody WantBookUpdateRequest updateRequest) {
        Long result = wantBookService.update(getUserId(), wantBookId, updateRequest);
        return new ResponseResult<>(result);
    }

    @DeleteMapping("/books/want/{want-book-id}")
    @Operation(summary = "특정 읽고 싶은 책 삭제", description = "특정한 읽고 싶은 책 정보를 삭제합니다.")
    public ResponseResult<EmptyResult> delete(@PathVariable("want-book-id") Long wantBookId) {
        wantBookService.delete(wantBookId);
        return new ResponseResult<>(new EmptyResult());
    }

    // 임시 처리 : JWT 작업 후 수정
    public Long getUserId() {
        return 1L;
    }
}
