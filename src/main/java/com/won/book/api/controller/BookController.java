package com.won.book.api.controller;

import com.won.book.common.response.ResponseResult;
import com.won.book.domain.book.BookDto;
import com.won.book.api.request.BookCreateRequest;
import com.won.book.domain.book.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "4. Book Controller")
public class BookController {

    private final BookService bookService;

    @ApiOperation(value = "책 검색 조회 API", notes = "")
    @GetMapping("/v1//books")
    public ResponseResult<List<BookDto>> getBooks(@RequestParam String keyword) {
        List<BookDto> bookDtos = bookService.getBooks(keyword);
        return new ResponseResult<>(bookDtos);
    }

    @ApiOperation(value = "책 상세 조회 API", notes = "")
    @GetMapping("/v1//books/{id}")
    public ResponseResult<BookDto> getBook(@PathVariable("id") Long bookId) {
        BookDto bookDto = bookService.getBook(bookId);
        return new ResponseResult<>(bookDto);
    }

    @ApiOperation(value = "책 저장 API", notes = "")
    @PostMapping("/v1//books")
    public ResponseResult<Long> save(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        return new ResponseResult<>(bookService.save(bookCreateRequest));
    }
}
