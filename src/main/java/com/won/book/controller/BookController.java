package com.won.book.controller;

import com.won.book.core.response.ResultResponse;
import com.won.book.dto.BookVo;
import com.won.book.dto.request.BookCreateRequest;
import com.won.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BookController
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/v1//book")
    public ResultResponse<List<BookVo>> getBooks(@RequestParam String keyword) {
        List<BookVo> bookVos = bookService.getBooks(keyword);
        return new ResultResponse<>(bookVos);
    }

    @GetMapping("/v1//book/{id}")
    public ResultResponse<BookVo> getBook(@PathVariable("id") Long bookId) {
        BookVo bookVo = bookService.getBook(bookId);
        return new ResultResponse<>(bookVo);
    }

    @PostMapping("/v1//book")
    public ResultResponse<Long> save(@RequestBody BookCreateRequest bookCreateRequest) {
        // 책 저장 : 추후 고려 필요(책을 등록한 사람의 책만 보여지도록 할 것인지!)
        return new ResultResponse<>(bookService.save(bookCreateRequest));
    }
}
