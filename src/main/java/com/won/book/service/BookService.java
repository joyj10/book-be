package com.won.book.service;

import com.won.book.converter.BookConverter;
import com.won.book.domain.Book;
import com.won.book.dto.BookVo;
import com.won.book.dto.request.BookCreateRequest;
import com.won.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BookService
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    @Transactional(readOnly = true)
    public BookVo getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        return bookConverter.convert(book);
    }

    @Transactional(readOnly = true)
    public List<BookVo> getBooks(String keyword) {
        List<Book> books = bookRepository.findAllByTitleContainingOrAuthorContaining(keyword, keyword);
        return bookConverter.convert(books);
    }

    public Long save(BookCreateRequest bookCreateRequest) {
        Book book = bookRepository.save(bookConverter.convert(bookCreateRequest));
        return book.getId();
    }
}
