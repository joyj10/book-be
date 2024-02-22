package com.won.bookappapi.service;

import com.won.bookappapi.api.request.BookCreateRequest;
import com.won.bookappapi.service.dto.BookDto;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("책을 조회한다.")
    @Test
    void getBook() {
        // given
        Book book = Book.builder()
                .title("java book")
                .author("박자바")
                .price(10000)
                .publisher("자바출판사")
                .isbn("1234")
                .sort("IT")
                .build();

        Book saveBook = bookRepository.save(book);

        // when
        BookDto findBook = bookService.getBook(saveBook.getId());

        // then
        assertThat(findBook.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(findBook.getTitle()).isEqualTo(book.getTitle());
    }

    @DisplayName("책을 저장한다.")
    @Test
    void save() {
        // given
        BookCreateRequest request = BookCreateRequest.builder()
                .title("java book")
                .author("박자바")
                .price(1000)
                .sort("IT")
                .publisher("출판사")
                .build();

        // when
        Long saveId = bookService.save(request);

        // then
        BookDto findBook = bookService.getBook(saveId);
        assertThat(findBook.getAuthor()).isEqualTo("박자바");
    }
}
