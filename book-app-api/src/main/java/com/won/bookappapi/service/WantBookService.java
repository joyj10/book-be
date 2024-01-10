package com.won.bookappapi.service;



import com.won.bookappapi.converter.BookConverter;
import com.won.bookappapi.service.dto.WantBookDto;
import com.won.bookdomain.domain.WantBook;
import com.won.bookdomain.repository.MemberRepository;
import com.won.bookdomain.repository.WantBookReasonRepository;
import com.won.bookdomain.repository.WantBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WantBookService {

    private final MemberRepository memberRepository;
    private final WantBookReasonRepository wantBookReasonRepository;
    private final WantBookRepository wantBookRepository;

    private final BookConverter bookConverter;

    @Transactional(readOnly = true)
    public List<WantBookDto> getList(Long memberId) {
        List<WantBook> wantBooks = wantBookRepository.findAllByMember(memberRepository.getReferenceById(memberId));

        List<WantBookDto> result = new ArrayList<>();
        for (WantBook wantBook : wantBooks) {
            result.add(convert(wantBook));
        }
        return result;
    }

    private WantBookDto convert(WantBook wantBook) {
        return WantBookDto.builder()
                .id(wantBook.getId())
                .book(bookConverter.convert(wantBook.getBook()))
                .build();
    }

    @Transactional(readOnly = true)
    public WantBookDto getDetail(Long memberId, Long wantBookId) {
        WantBook wantBook = wantBookRepository.findByIdAndMember(wantBookId, memberRepository.getReferenceById(memberId))
                .orElseThrow(IllegalArgumentException::new);
        return convert(wantBook);
    }
}
