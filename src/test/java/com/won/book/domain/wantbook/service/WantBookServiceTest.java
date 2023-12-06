package com.won.book.domain.wantbook.service;

import com.won.book.IntegrationTestSupport;
import com.won.book.domain.book.Book;
import com.won.book.domain.book.repository.BookRepository;
import com.won.book.domain.member.Member;
import com.won.book.domain.member.repository.MemberRepository;
import com.won.book.domain.wantbook.dto.WantBookDto;
import com.won.book.domain.wantbook.entity.WantBook;
import com.won.book.domain.wantbook.repository.WantBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class WantBookServiceTest extends IntegrationTestSupport {

    @Autowired
    private WantBookService wantBookService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WantBookRepository wantBookRepository;

    @DisplayName("읽고 싶은 책 리스트를 조회한다.")
    @Test
    void getWantBookList() {
        // given
        WantBook saveWantBook = saveWantBook();
        Long memberId = saveWantBook.getMember().getId();

        // when
        List<WantBookDto> wantBookDtos = wantBookService.getList(memberId);

        // then
        assertThat(wantBookDtos).hasSize(1);
        assertThat(wantBookDtos.get(0).getBook())
                .extracting("title", "author")
                .contains(saveWantBook.getBook().getTitle(), saveWantBook.getBook().getAuthor());
    }

    private Member saveMember() {
        return memberRepository.save(Member.builder()
                .nickname("회원")
                .email("test@test.com")
                .password("test")
                .build());
    }

    private Book saveBook() {
        return bookRepository.save(Book.builder()
                .title("개발책")
                .author("김자바")
                .price(10000)
                .sort("IT")
                .publisher("출판사")
                .isbn("12345")
                .build());

    }

    private WantBook saveWantBook() {
        Member saveMember = saveMember();
        Book saveBook = saveBook();

        return wantBookRepository.save(WantBook.builder()
                        .isDeleted(false)
                        .member(saveMember)
                        .book(saveBook)
                        .build());
    }


}
