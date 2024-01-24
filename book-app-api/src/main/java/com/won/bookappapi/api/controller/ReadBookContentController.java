package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.ReadBookContentCreateRequest;
import com.won.bookappapi.service.ReadBookContentService;
import com.won.bookappapi.service.dto.ReadBookContentDto;
import com.won.bookcommon.response.EmptyResult;
import com.won.bookcommon.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "3. Read Book content Controller")
public class ReadBookContentController {

    private final ReadBookContentService readBookContentService;

    @GetMapping("/vi/book/read/content")
    @ApiOperation(value = "읽은 책 글귀 리스트")
    public ResponseResult<List<ReadBookContentDto>> getContents(Long readBookId) {
        List<ReadBookContentDto> result = readBookContentService.getContents(readBookId);
        return new ResponseResult<>(result);
    }

    @PostMapping("/vi/book/read/content")
    @ApiOperation(value = "읽은 책 글귀 저장 하기")
    public ResponseResult<EmptyResult> save(@Valid @RequestBody ReadBookContentCreateRequest createRequest) {
        readBookContentService.save(createRequest);
        return new ResponseResult<>(new EmptyResult());
    }

    @PatchMapping("/vi/book/read/content/{content-id}")
    @ApiOperation(value = "읽은 책 글귀 수정 하기")
    public ResponseResult<EmptyResult> update(@PathVariable("content-id") Long contentId,
                                              @Valid @RequestBody String content) {
        readBookContentService.update(contentId, content);
        return new ResponseResult<>(new EmptyResult());
    }

    @DeleteMapping("/vi/book/read/content/{content-id}")
    @ApiOperation(value = "읽은 책 글귀 삭제 하기")
    public String delete(@PathVariable("content-id") Long contentId, String str) {
        return "Hello, " + str;
    }
}
