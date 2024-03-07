package com.won.bookappapi.service;

import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.api.request.ReadBookUpdateRequest;
import com.won.bookappapi.api.request.YearMonthRequest;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookcommon.exception.BusinessException;
import com.won.bookdomain.code.UserAuthRole;
import com.won.bookdomain.domain.Book;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import com.won.bookdomain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
@Transactional
class ReadBookServiceTest {

    @Autowired ReadBookService readBookService;
    @Autowired ReadBookRepository readBookRepository;
    @Autowired UserRepository userRepository;
    @Autowired BookRepository bookRepository;

    private User user;
    private List<Book> books = new ArrayList<>();

    @BeforeEach
    void init() {
        this.user = userRepository.save(User.builder()
                .name("박자바")
                .email("test@test.com")
                .password("test1234")
                .authority(UserAuthRole.USER)
                .build());

        books.add(bookRepository.save(creaetBook("java study", "a123")));
        books.add(bookRepository.save(creaetBook("spring study", "b123")));
    }

    private static Book creaetBook(String title, String isbn) {
        return Book.builder()
                .title(title)
                .author("박저자")
                .price(10000)
                .publisher("출판사")
                .isbn(isbn)
                .sort("IT")
                .build();
    }

    private static ReadBook getReadBook(int totalRating, Book book, User user, LocalDate readAt) {
        ReadBook readBook = ReadBook.create(1, totalRating, readAt);
        readBook.setMemberAndBook(user, book);

        return readBook;
    }

    @DisplayName("회원 읽은 책 전체를 조회 한다.")
    @Test
    void getBooks() {
        // given
        Book book1 = books.get(0);
        Book book2 = books.get(1);

        List<ReadBook> saveReadBooks = new ArrayList<>();
        saveReadBooks.add(getReadBook(5, book1, user, LocalDate.of(2024, 1, 1)));
        saveReadBooks.add(getReadBook(4, book2, user, LocalDate.of(2024, 1, 1)));
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

    @DisplayName("회원 읽은 책 상세를 조회한다.")
    @Test
    void getReadBook() {
        // given
        Book book = books.get(0);
        ReadBook readBook = readBookRepository.save(getReadBook(5, book, user, LocalDate.of(2024, 1, 1)));

        // when
        ReadBookDto findReadBookDto = readBookService.getReadBook(user.getId(), readBook.getId());

        // then
        assertThat(findReadBookDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(findReadBookDto.getSort()).isEqualTo(book.getSort());
        assertThat(findReadBookDto.getImage()).isNull();
        assertThat(findReadBookDto.getAuthor()).isEqualTo(book.getAuthor());
        assertThat(findReadBookDto.getReadAt()).isNotNull();
        assertThat(findReadBookDto.getPrice()).isEqualTo(book.getPrice());
        assertThat(findReadBookDto.getReadBookId()).isEqualTo(readBook.getId());
    }

    @DisplayName("읽은 책을 저장한다.")
    @Test
    void save() {
        // given
        Book book = books.get(0);

        Long userId = user.getId();
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(book.getId())
                .readAt("2024-01-01")
                .rating(4)
                .reviews(List.of("재미 있는 책"))
                .contents(List.of("좋은 글귀1", "좋은 글귀2"))
                .build();

        // when
        Long saveId = readBookService.save(userId, request);

        // then
        ReadBook findReadBook = readBookRepository.findById(saveId).orElseThrow();

        assertThat(findReadBook.getBook().getId()).isEqualTo(book.getId());
        assertThat(findReadBook.getLastReadAt()).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(findReadBook.getTotalRating()).isEqualTo(4);
        assertThat(findReadBook.getReadBookReviews()).hasSize(1)
                .extracting("review")
                .containsExactlyInAnyOrder("재미 있는 책");
        assertThat(findReadBook.getReadBookContents()).hasSize(2)
                .extracting("content")
                .containsExactlyInAnyOrder("좋은 글귀1", "좋은 글귀2");
    }

    @DisplayName("읽은 책을 저장 시 읽은 날 데이터가 없으면 예외가 발생 한다.")
    @Test
    void save_fail_readAt_empty() {
        // given
        Book book = books.get(0);

        Long userId = user.getId();
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(book.getId())
                .readAt("")
                .rating(4)
                .reviews(List.of("재미 있는 책"))
                .contents(List.of("좋은 글귀1", "좋은 글귀2"))
                .build();

        // when // then
        thenThrownBy(() -> readBookService.save(userId, request))
                .isExactlyInstanceOf(DateTimeParseException.class);
    }

    @DisplayName("읽은 책을 저장 시 평점이 5 초과인 경우 예외가 발생 한다.")
    @Test
    void save_fail_rating_over() {
        // given
        Book book = books.get(0);

        Long userId = user.getId();
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(book.getId())
                .readAt("2024-01-01")
                .rating(6)
                .reviews(List.of("재미 있는 책"))
                .contents(List.of("좋은 글귀1", "좋은 글귀2"))
                .build();

        // when // then
        thenThrownBy(() -> readBookService.save(userId, request))
                .isExactlyInstanceOf(BusinessException.class);
    }

    @DisplayName("읽은 책을 저장 시 평점이 음수인 경우 예외가 발생 한다.")
    @Test
    void save_fail_rating_negative_number() {
        // given
        Book book = books.get(0);

        Long userId = user.getId();
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(book.getId())
                .readAt("2024-01-01")
                .rating(-1)
                .reviews(List.of("재미 있는 책"))
                .contents(List.of("좋은 글귀1", "좋은 글귀2"))
                .build();

        // when // then
        thenThrownBy(() -> readBookService.save(userId, request))
                .isExactlyInstanceOf(BusinessException.class);
    }

    @DisplayName("읽은 책 데이터가 수정 된다.")
    @Test
    void update() {
        // given
        Book book = books.get(0);
        ReadBook readBook = readBookRepository.save(getReadBook(5, book, user, LocalDate.of(2024, 1, 1)));

        ReadBookUpdateRequest updateRequest = ReadBookUpdateRequest.builder()
                .readAt("2024-02-01")
                .rating(3)
                .build();

        // when
        Long updateId = readBookService.update(user.getId(), readBook.getId(), updateRequest);

        // then
        ReadBook findReadBook = readBookRepository.findById(updateId).orElseThrow();

        assertThat(findReadBook.getLastReadAt()).isEqualTo(LocalDate.of(2024, 2, 1));
        assertThat(findReadBook.getTotalRating()).isEqualTo(3);
    }

    @DisplayName("읽은 책을 삭제한다.")
    @Test
    void delete() {
        // given
        Book book = books.get(0);
        ReadBook readBook = readBookRepository.save(getReadBook(5, book, user, LocalDate.of(2024, 1, 1)));

        // when
        readBookService.delete(user.getId(), readBook.getId());

        // then
        ReadBook findReadBook = readBookRepository.findById(readBook.getId()).orElseThrow();

        assertThat(findReadBook.isDeleted()).isTrue();
    }

    @DisplayName("읽은 책의 삭제 여부가 삭제된 책을 재삭제하려는 경우 예외가 발생 한다.")
    @Test
    void delete_isDeleted() {
        // given
        Book book = books.get(0);
        ReadBook readBook = getReadBook(5, book, user, LocalDate.of(2024, 1, 1));
        readBook.deleted();
        ReadBook saveReadBook = readBookRepository.save(readBook);

        // when // then
        thenThrownBy(() -> readBookService.delete(user.getId(), saveReadBook.getId()))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Disabled
    @DisplayName("멤버의 해당 월의 읽은 책 전체를 조회 한다.")
    @Test
    void getReadBookOfMonth() {
        // given
        LocalDate localDate = LocalDate.now();

        Book book1 = bookRepository.save(creaetBook("java study", "a123"));
        Book book2 = bookRepository.save(creaetBook("spring study", "b123"));
        Book book3 = bookRepository.save(creaetBook("spring study2", "c123"));

        List<ReadBook> saveReadBooks = new ArrayList<>();
        saveReadBooks.add(getReadBook(5, book1, user, LocalDate.of(2024, 2, 1)));
        saveReadBooks.add(getReadBook(4, book2, user, LocalDate.of(2024, 2, 1)));

        // 전월 읽은 책 추가
        ReadBook lastMonthReadBook = getReadBook(4, book3, user, LocalDate.of(2024, 1, 1));
        lastMonthReadBook.update(localDate.minusMonths(1L), 4);

        readBookRepository.saveAll(saveReadBooks);

        YearMonthRequest request = YearMonthRequest.builder()
                .year(localDate.getYear())
                .month(localDate.getMonth().getValue())
                .build();

        // when
        List<ReadBookDto> findReadBooks = readBookService.getReadBookOfMonth(user.getId(), request);

        // then
        assertThat(findReadBooks).hasSize(2)
                .extracting("title", "sort", "author")
                .containsExactlyInAnyOrder(
                        tuple(book1.getTitle(), book1.getSort(), book1.getAuthor()),
                        tuple(book2.getTitle(), book2.getSort(), book2.getAuthor())
                );
    }
}
