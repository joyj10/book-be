package com.won.bookappapi.service;

import com.won.bookappapi.api.request.BookCreateRequest;
import com.won.bookappapi.service.dto.BookDto;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @DisplayName("책을 저장한다.")
    @Test
    void saveBook() {
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
