package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.BookCreateRequest;
import com.won.bookappapi.service.BookService;
import com.won.bookappapi.service.dto.BookDto;
import com.won.bookcommon.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Book Controller", description = "책 API")
public class BookController {

    private final BookService bookService;

    @GetMapping("//books")
    @Operation(summary = "책 검색 조회", description = "원하는 책들을 키워드 검색 조회합니다.")
    public ResponseResult<List<BookDto>> getBooks(@RequestParam String keyword) {
        List<BookDto> bookDtos = bookService.getBooks(keyword);
        return new ResponseResult<>(bookDtos);
    }

    @GetMapping("//books/{id}")
    @Operation(summary = "책 상세 조회", description = "특정 책의 상세 정보를 조회합니다.")
    public ResponseResult<BookDto> getBook(@PathVariable("id") Long bookId) {
        BookDto bookDto = bookService.getBook(bookId);
        return new ResponseResult<>(bookDto);
    }

    @PostMapping("//books")
    @Operation(summary = "책 저장", description = "검색 시 책이 없는 경우 책을 저장합니다.")
    public ResponseResult<Long> save(@Valid @RequestBody BookCreateRequest bookCreateRequest) {
        return new ResponseResult<>(bookService.save(bookCreateRequest));
    }
}
