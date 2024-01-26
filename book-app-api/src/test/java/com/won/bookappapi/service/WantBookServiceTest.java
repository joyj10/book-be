package com.won.bookappapi.service;

import com.won.bookappapi.api.request.WantBookCreateRequest;
import com.won.bookdomain.code.UserAuthRole;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.WantBook;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.UserRepository;
import com.won.bookdomain.repository.WantBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Slf4j
@Transactional
class WantBookServiceTest {

    @Autowired private BookRepository bookRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private WantBookRepository wantBookRepository;
    @Autowired private WantBookService wantBookService;

    private WantBook wantBook;

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

        wantBook = wantBookRepository.save(WantBook.builder()
                        .user(user)
                        .book(book)
                        .isDeleted(false)
                        .build());
    }

    @DisplayName("읽고 싶은 책을 저장한다.")
    @Test
    void save() {
        // given
        init();
        WantBookCreateRequest createRequest = WantBookCreateRequest.builder()
                .bookId(wantBook.getBook().getId())
                .reason("읽고 싶은 책")
                .build();

        // when
        Long saveWantBookId = wantBookService.save(wantBook.getUser().getId(), createRequest);

        // then
        WantBook findWantBook = wantBookRepository.findById(saveWantBookId)
                .orElseThrow();

        then(findWantBook.getBook().getId()).isEqualTo(createRequest.getBookId());
        then(findWantBook.getWantBookReasons().get(0).getReason()).isEqualTo(createRequest.getReason());
    }

    @DisplayName("읽고 싶은 책을 삭제한다.")
    @Test
    void delete() {
        // given
        init();
        Long wantBookId = wantBook.getId();

        // when
        wantBookService.delete(wantBookId);

        // then
        Optional<WantBook> opt = wantBookRepository.findById(wantBookId);
        then(opt.isPresent()).isFalse();
    }
}
