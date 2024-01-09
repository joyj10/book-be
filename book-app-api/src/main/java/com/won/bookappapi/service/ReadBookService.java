package com.won.bookappapi.service;

import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.api.request.ReadBookUpdateRequest;
import com.won.bookappapi.converter.ReadBookConverter;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.domain.ReadBookContent;
import com.won.bookdomain.domain.ReadBookRating;
import com.won.bookdomain.domain.ReadBookReview;
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
    public List<ReadBookDto> getList(Long memberId) {
        List<ReadBook> readBooks = readBookRepository.findAllByMember(memberRepository.getReferenceById(memberId));
        return readBookConverter.convert(readBooks);
    }

    @Transactional(readOnly = true)
    public ReadBookDto getDetail(Long memberId, Long readBookId) {
        ReadBook readBook = readBookRepository.findByIdAndMember(readBookId, memberRepository.getReferenceById(memberId))
                .orElseThrow(IllegalArgumentException::new);
        return readBookConverter.convert(readBook);
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

    @Transactional
    public Long save(Long memberId, ReadBookCreateRequest request) {
        ReadBook readBook = request.toEntity();
        readBook.setMemberAndBook(memberRepository.getReferenceById(memberId), bookRepository.getReferenceById(request.getBookId()));
        readBook.addReadBookRating(ReadBookRating.createFirst(readBook.getLastReadAt(), readBook.getTotalRating()));

        List<ReadBookContent> contents = new ArrayList<>();
        for (String content : request.getContents()) {
            contents.add(ReadBookContent.create(content));
        }
        readBook.addReadBookContent(contents);

        List<ReadBookReview> reviews = new ArrayList<>();
        for (String review : request.getReviews()) {
            reviews.add(ReadBookReview.create(review));
        }
        readBook.addReadBookReview(reviews);

        ReadBook saveReadBook = readBookRepository.save(readBook);
        return saveReadBook.getId();
    }

    @Transactional
    public Long update(Long memberId, Long readBookId, ReadBookUpdateRequest request) {
        ReadBook readBook = readBookRepository.findByIdAndMember(readBookId, memberRepository.getReferenceById(memberId))
                .orElseThrow(IllegalArgumentException::new);
        readBook.update(LocalDateTimeUtil.toLocalDate(request.getReadAt()), request.getRating());
        return readBook.getId();
    }
}
