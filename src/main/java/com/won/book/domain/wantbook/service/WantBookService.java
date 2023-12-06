package com.won.book.domain.wantbook.service;

import com.won.book.converter.BookConverter;
import com.won.book.domain.member.repository.MemberRepository;
import com.won.book.domain.wantbook.dto.WantBookDto;
import com.won.book.domain.wantbook.entity.WantBook;
import com.won.book.domain.wantbook.repository.WantBookReasonRepository;
import com.won.book.domain.wantbook.repository.WantBookRepository;
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
            result.add(
                    WantBookDto.builder()
                        .id(wantBook.getId())
                        .book(bookConverter.convert(wantBook.getBook()))
                        .build());
        }
        return result;
    }
}
