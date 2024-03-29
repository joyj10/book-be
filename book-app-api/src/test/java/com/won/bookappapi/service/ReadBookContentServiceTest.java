package com.won.bookappapi.service;

import com.won.bookappapi.api.request.ReadBookContentCreateRequest;
import com.won.bookappapi.api.request.ReadBookContentUpdateRequest;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@SpringBootTest
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
                .deleted(false)
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
        ReadBookContentUpdateRequest updateRequest = ReadBookContentUpdateRequest.builder()
                .content(changeContent)
                .build();

        // when
        contentService.update(contentId, updateRequest);

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
        ReadBookContentUpdateRequest updateRequest = ReadBookContentUpdateRequest.builder()
                .content(changeContent)
                .build();


        // when then
        thenThrownBy(() -> contentService.update(contentId, updateRequest))
                .isExactlyInstanceOf(BusinessException.class);
    }

    @DisplayName("읽은 책 content를 삭제한다.")
    @Test
    void delete() {
        // given
        init();
        Long contentId = readBook.getReadBookContents().get(0).getId();

        // when
        contentService.delete(contentId);

        // then
        Optional<ReadBookContent> opt = contentRepository.findById(contentId);
        then(opt.isPresent()).isFalse();
    }

}
