package com.won.bookappapi.service;

import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.api.request.ReadBookUpdateRequest;
import com.won.bookappapi.converter.ReadBookConverter;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.*;
import com.won.bookdomain.repository.BookRepository;
import com.won.bookdomain.repository.MemberRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReadBookService {

    private final ReadBookRepository readBookRepository;
    private final ReadBookConverter readBookConverter;

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    /**
     * 회원 읽은 책 전체 조회
     * @param memberId 회원 아이디
     * @return List<ReadBookDto>
     */
    @Transactional(readOnly = true)
    public List<ReadBookDto> getReadBooks(Long memberId) {
        List<ReadBook> readBooks = readBookRepository.findAllByMember(memberRepository.getReferenceById(memberId));
        return readBookConverter.convert(readBooks);
    }

    @Transactional(readOnly = true)
    public ReadBookDto getReadBook(Long memberId, Long readBookId) {
        ReadBook readBook = readBookRepository.findByIdAndMember(readBookId, memberRepository.getReferenceById(memberId))
                .orElseThrow(IllegalArgumentException::new);
        return readBookConverter.convert(readBook);
    }

    @Transactional
    public Long save(Long memberId, ReadBookCreateRequest request) {
        ReadBook readBook = createReadBook(memberId, request);
        return readBookRepository.save(readBook).getId();
    }

    private ReadBook createReadBook(Long memberId, ReadBookCreateRequest request) {
        ReadBook readBook = request.toEntity();

        // 연관 관계 세팅
        Member member = memberRepository.getReferenceById(memberId);
        Book book = bookRepository.getReferenceById(request.getBookId());
        readBook.setMemberAndBook(member, book);

        ReadBookRating readBookRating = ReadBookRating.createFirst(readBook.getLastReadAt(), readBook.getTotalRating());
        readBook.addReadBookRating(readBookRating);

        addReadBookContent(request.getContents(), readBook);
        addReadBookReviews(request.getReviews(), readBook);

        return readBook;
    }

    private static void addReadBookReviews(List<String> reviews, ReadBook readBook) {
        List<ReadBookReview> saveReviews = new ArrayList<>();
        for (String review : reviews) {
            saveReviews.add(ReadBookReview.create(review));
        }
        readBook.addReadBookReview(saveReviews);
    }

    private static void addReadBookContent(List<String> contents, ReadBook readBook) {
        List<ReadBookContent> saveContents = new ArrayList<>();
        for (String content : contents) {
            saveContents.add(ReadBookContent.create(content));
        }
        readBook.addReadBookContent(saveContents);
    }

    @Transactional
    public Long update(Long memberId, Long readBookId, ReadBookUpdateRequest request) {
        ReadBook readBook = readBookRepository.findByIdAndMember(readBookId, memberRepository.getReferenceById(memberId))
                .orElseThrow(IllegalArgumentException::new);
        readBook.update(LocalDateTimeUtil.toLocalDate(request.getReadAt()), request.getRating());
        return readBook.getId();
    }

    @Transactional
    public void delete(Long memberId, Long readBookId) throws BadRequestException {
        ReadBook readBook = readBookRepository.findByIdAndMember(readBookId, memberRepository.getReferenceById(memberId))
                .orElseThrow(IllegalArgumentException::new);

        if (readBook.isDeleted()) {
            throw new BadRequestException();
        }
        readBook.deleted();
    }
}
