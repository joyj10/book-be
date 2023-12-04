package com.won.book.domain.book;

import com.won.book.IntegrationTestSupport;
import com.won.book.api.request.BookCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class BookServiceTest extends IntegrationTestSupport {

    @Autowired private BookService bookService;
    @Autowired private BookRepository bookRepository;

    @DisplayName("책 저장 시 입력한 데이터가 정상적으로 들어간다.")
    @Test
    void save() {
        // given
        BookCreateRequest request = BookCreateRequest.builder()
                .title("개발책")
                .author("김자바")
                .price(10000)
                .sort("IT")
                .build();

        // when
        Long saveBookId = bookService.save(request);

        // then
        Book findBook = bookRepository.findById(saveBookId).orElse(null);
        assertThat(findBook).isNotNull()
                .extracting("title", "author", "isbn")
                .contains(request.getTitle(), request.getAuthor(), "NONE");
    }

    @DisplayName("책 조회 시 저장 한 데이터를 그대로 가져온다.")
    @Test
    void getBook() {
        // given
        Book saveBook = bookRepository.save(Book.builder()
                .title("개발책")
                .author("김자바")
                .price(10000)
                .sort("IT")
                .publisher("출판사")
                .isbn("12345")
                .build());

        // when
        BookVo findBook = bookService.getBook(saveBook.getId());

        // then
        assertThat(findBook).isNotNull()
                .extracting("bookId", "title", "author", "image")
                .contains(saveBook.getId(), saveBook.getTitle(), saveBook.getAuthor(), saveBook.getImage());
    }
}
