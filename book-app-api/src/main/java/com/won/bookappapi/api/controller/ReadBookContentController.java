package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.ReadBookContentCreateRequest;
import com.won.bookappapi.service.ReadBookContentService;
import com.won.bookappapi.service.dto.ReadBookContentDto;
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
@Tag(name = "Read Book content Controller", description = "읽은 책 글귀 API")
public class ReadBookContentController {

    private final ReadBookContentService readBookContentService;

    @GetMapping("/books/read/contents")
    @Operation(summary = "읽은 책 글귀 리스트 조회", description = "읽은 책에 저장된 좋은 글귀 리스트를 조회합니다.")
    public ResponseResult<List<ReadBookContentDto>> getContents(Long readBookId) {
        List<ReadBookContentDto> result = readBookContentService.getContents(readBookId);
        return new ResponseResult<>(result);
    }

    @PostMapping("/books/read/contents")
    @Operation(summary = "읽은 책 글귀 저장", description = "읽은 책의 좋은 글귀를 추가 저장합니다.")
    public ResponseResult<EmptyResult> save(@Valid @RequestBody ReadBookContentCreateRequest createRequest) {
        readBookContentService.save(createRequest);
        return new ResponseResult<>(new EmptyResult());
    }

    @PatchMapping("/books/read/contents/{content-id}")
    @Operation(summary = "읽은 책의 글귀 수정", description = "읽은 책의 특정 글귀를 수정합니다.")
    public ResponseResult<EmptyResult> update(@PathVariable("content-id") Long contentId,
                                              @Valid @RequestBody String content) {
        readBookContentService.update(contentId, content);
        return new ResponseResult<>(new EmptyResult());
    }

    @DeleteMapping("/books/read/contents/{content-id}")
    @Operation(summary = "읽은 책의 특정 글귀 삭제", description = "읽은 책의 특정 글귀를 삭제합니다.")
    public ResponseResult<EmptyResult> delete(@PathVariable("content-id") Long contentId) {
        readBookContentService.delete(contentId);
        return new ResponseResult<>(new EmptyResult());
    }
}
