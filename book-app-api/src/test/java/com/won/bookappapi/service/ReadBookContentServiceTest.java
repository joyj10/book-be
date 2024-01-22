package com.won.bookappapi.service;

import com.won.bookappapi.service.dto.ReadBookContentDto;
import com.won.bookdomain.code.UserAuthRole;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.domain.ReadBookContent;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.ReadBookContentRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import com.won.bookdomain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Slf4j
@Transactional
class ReadBookContentServiceTest {
    @Autowired private ReadBookRepository readBookRepository;
    @Autowired private BookRepository bookRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ReadBookContentRepository contentRepository;
    @Autowired private ReadBookContentService contentService;

    private ReadBook readBook;
    List<ReadBookContent> contents = new ArrayList<>();

    void init() {
        User user = userRepository.save(User.builder()
                        .name("박자바")
                        .email("test@test.com")
                        .password("test1234")
                        .authority(UserAuthRole.USER)
                        .build());

        Book book = bookRepository.save(Book.builder()
                .title("자바 개론")
                .author("박저자")
                .price(10000)
                .publisher("출판사")
                .isbn("abc123")
                .sort("IT")
                .build());


        contents.add(ReadBookContent.create("좋은 글귀"));

        readBook = ReadBook.builder()
                .readCount(1)
                .totalRating(5)
                .lastReadAt(LocalDate.now())
                .isDeleted(false)
                .book(book)
                .user(user)
                .build();

        readBook.addReadBookContent(contents);
        readBook = readBookRepository.save(readBook);
    }

    @DisplayName("읽은 책의 글귀를 조회한다.")
    @Test
    void getContents() {
        // given
        init();

        // when
        List<ReadBookContentDto> findContents = contentService.getContents(readBook.getId());

        // then
        then(findContents).hasSize(contents.size());
        then(findContents.get(0).getContent()).isEqualTo(contents.get(0).getContent());
    }

}
