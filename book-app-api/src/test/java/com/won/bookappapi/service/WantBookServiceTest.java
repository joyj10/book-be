package com.won.bookappapi.service;

import com.won.bookappapi.api.request.WantBookCreateRequest;
import com.won.bookappapi.api.request.WantBookUpdateRequest;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.code.UserAuthRole;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.WantBook;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.UserRepository;
import com.won.bookdomain.repository.WantBookRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
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
                        .addAt(LocalDate.now())
                        .deleted(false)
                        .build());
    }

    @DisplayName("읽고 싶은 책을 저장한다.")
    @Test
    void save() {
        // given
        init();
        String reason = "읽고 싶은 책";
        WantBookCreateRequest createRequest = WantBookCreateRequest.builder()
                .bookId(wantBook.getBook().getId())
                .addAt("2024-01-01")
                .reasons(List.of(reason))
                .build();

        // when
        Long saveWantBookId = wantBookService.save(wantBook.getUser().getId(), createRequest);

        // then
        WantBook findWantBook = wantBookRepository.findById(saveWantBookId).orElseThrow();

        then(findWantBook.getBook().getId()).isEqualTo(createRequest.getBookId());
        then(findWantBook.getWantBookReasons().get(0).getReason()).isEqualTo(reason);
    }

    @DisplayName("읽고 싶은 책을 수정한다.")
    @Test
    void update() {
        // given
        init();
        String reason = "읽고 싶은 책 수정 이유";
        String addAt = "2024-01-10";
        WantBookUpdateRequest updateRequest = WantBookUpdateRequest.builder()
                .addAt(addAt)
                .reasons(List.of(reason))
                .build();
        // when
        Long updateWantBookId = wantBookService.update(wantBook.getUser().getId(), wantBook.getId(), updateRequest);

        // then
        WantBook findWantBook = wantBookRepository.findById(updateWantBookId).orElseThrow();

        then(findWantBook.getAddAt()).isEqualTo(LocalDateTimeUtil.toLocalDate(addAt));
        then(findWantBook.getWantBookReasons().get(0).getReason()).isEqualTo(reason);
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
