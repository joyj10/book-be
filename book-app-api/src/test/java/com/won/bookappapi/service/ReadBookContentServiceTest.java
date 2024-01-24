package com.won.bookappapi.service;

import com.won.bookappapi.api.request.ReadBookContentCreateRequest;
import com.won.bookappapi.service.dto.ReadBookContentDto;
import com.won.bookcommon.exception.BusinessException;
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
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

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

    @DisplayName("읽은 책 content를 조회한다.")
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

    @DisplayName("읽은 책 content를 저장한다.")
    @Test
    void save() {
        // given
        init();
        ReadBookContentCreateRequest createRequest = ReadBookContentCreateRequest.builder()
                .readBookId(readBook.getId())
                .contents(List.of("좋은 글귀1", "좋은 글귀2"))
                .build();

        // when
        contentService.save(createRequest);

        // then
        ReadBook findReadBook = readBookRepository.findById(readBook.getId())
                .orElseThrow();

        List<ReadBookContent> readBookContents = findReadBook.getReadBookContents();
        then(readBookContents).hasSize(3)
                .extracting("content")
                .containsExactlyInAnyOrder("좋은 글귀1", "좋은 글귀2", "좋은 글귀");
    }

    @DisplayName("읽은 책 content를 수정한다.")
    @Test
    void update() {
        // given
        init();
        Long contentId = readBook.getReadBookContents().get(0).getId();
        String changeContent = "변경된 문장";

        // when
        contentService.update(contentId, changeContent);

        // then
        ReadBookContent findContent = contentRepository.findById(contentId)
                .orElseThrow();
        then(findContent.getContent())
                .isEqualTo(changeContent);
    }

    @DisplayName("읽은 책 content 수정 시 빈값 전달하면 예외가 발생한다.")
    @Test
    void updateFailContentBlank() {
        // given
        init();
        Long contentId = readBook.getReadBookContents().get(0).getId();
        String changeContent = "";

        // when then
        thenThrownBy(() -> contentService.update(contentId, changeContent))
                .isExactlyInstanceOf(BusinessException.class);
    }

}
