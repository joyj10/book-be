package com.won.book.domain.book;

import com.won.book.converter.BookConverter;
import com.won.book.api.request.BookCreateRequest;
import com.won.book.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public List<BookDto> getBooks(String keyword) {
        // DB 조회가 아닌 네이버 책 API 호출로 변경
        // https://developers.naver.com/docs/serviceapi/search/book/book.md#%EC%B1%85
        return new ArrayList<>();
    }

    public BookDto getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return bookConverter.convert(book);
    }

    @Transactional
    public Long save(BookCreateRequest bookCreateRequest) {
        Book book = bookCreateRequest.toEntity();
        return bookRepository.save(book).getId();
    }
}
