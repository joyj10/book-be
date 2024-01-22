package com.won.bookappapi.service;

import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookdomain.code.UserAuthRole;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.UserRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
class ReadBookServiceTest {

    @Autowired ReadBookService readBookService;
    @Autowired ReadBookRepository readBookRepository;
    @Autowired UserRepository userRepository;
    @Autowired BookRepository bookRepository;

    private User user;

    @BeforeEach
    void init() {
        this.user = userRepository.save(User.builder()
                .name("박자바")
                .email("test@test.com")
                .password("test1234")
                .authority(UserAuthRole.USER)
                .build());
    }

    private static ReadBook getReadBook(int totalRating, Book book, User user) {
        return ReadBook.builder()
                .readCount(1)
                .totalRating(totalRating)
                .lastReadAt(LocalDate.now())
                .isDeleted(false)
                .book(book)
                .user(user)
                .build();
    }

    private static Book getBook(String title, String isbn) {
        return Book.builder()
                .title(title)
                .author("박저자")
                .price(10000)
                .publisher("출판사")
                .isbn(isbn)
                .sort("IT")
                .build();
    }

    @DisplayName("멤버의 읽은 책 전체를 조회 한다.")
    @Test
    void getBooks() {
        // given
        Book book1 = bookRepository.save(getBook("java study", "a123"));
        Book book2 = bookRepository.save(getBook("spring study", "b123"));

        List<ReadBook> saveReadBooks = new ArrayList<>();
        saveReadBooks.add(getReadBook(5, book1, user));
        saveReadBooks.add(getReadBook(4, book2, user));
        readBookRepository.saveAll(saveReadBooks);

        // when
        List<ReadBookDto> findReadBooks = readBookService.getReadBooks(user.getId());

        // then
        assertThat(findReadBooks).hasSize(2)
                .extracting("title", "sort", "author")
                .containsExactlyInAnyOrder(
                        tuple(book1.getTitle(), book1.getSort(), book1.getAuthor()),
                        tuple(book2.getTitle(), book2.getSort(), book2.getAuthor())
                );
    }

}
