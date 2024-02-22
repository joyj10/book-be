package com.won.bookappapi.service;

import com.won.bookappapi.api.request.WantBookCreateRequest;
import com.won.bookappapi.api.request.WantBookUpdateRequest;
import com.won.bookappapi.service.dto.WantBookDto;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.code.UserAuthRole;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.WantBook;
import com.won.bookdomain.domain.WantBookReason;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.UserRepository;
import com.won.bookdomain.repository.WantBookReasonRepository;
import com.won.bookdomain.repository.WantBookRepository;
import jakarta.persistence.EntityManager;
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
    @Autowired private WantBookReasonRepository wantBookReasonRepository;
    @Autowired private WantBookService wantBookService;
    @Autowired private EntityManager em;

    private User user;
    private WantBook wantBook;

    void init() {
        user = userRepository.save(User.builder()
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

        WantBook saveWantBook = WantBook.builder()
                .user(user)
                .book(book)
                .addAt(LocalDate.now())
                .deleted(false)
                .build();

        WantBookReason reason1 = WantBookReason.create("재미 있는 책");
        WantBookReason reason2 = WantBookReason.create("추천 받은 책");
        saveWantBook.addWantBookReasons(List.of(reason1, reason2));

        wantBook = wantBookRepository.save(saveWantBook);
        em.flush();
        em.clear();
    }

    @DisplayName("회원이 저장한 읽고 싶은 책 리스트를 조회한다.")
    @Test
    void getWantBooks() {
        // given
        init();
        long userId = user.getId();

        // when
        List<WantBookDto> findWantBooks = wantBookService.getWantBooks(userId);

        // then
        then(findWantBooks).hasSize(1);
        then(findWantBooks.get(0).getBook().getTitle()).isEqualTo(wantBook.getBook().getTitle());
        then(findWantBooks.get(0).getBook().getAuthor()).isEqualTo(wantBook.getBook().getAuthor());
        then(findWantBooks.get(0).getBook().getBookId()).isEqualTo(wantBook.getBook().getId());
    }

    @DisplayName("회원이 저장한 읽고 싶은 책의 상세를 조회한다.")
    @Test
    void getWantBook() {
        // given
        init();
        long userId = user.getId();
        long wantBookId = wantBook.getId();

        // when
        WantBookDto findWantBook = wantBookService.getWantBook(userId, wantBookId);

        // then
        then(findWantBook.getId()).isEqualTo(wantBookId);
        then(findWantBook.getBook().getBookId()).isEqualTo(wantBook.getBook().getId());
        then(findWantBook.getBook().getSort()).isEqualTo(wantBook.getBook().getSort());
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
        int size = findWantBook.getWantBookReasons().size();
        then(findWantBook.getWantBookReasons().get(size-1).getReason()).isEqualTo(reason);
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
