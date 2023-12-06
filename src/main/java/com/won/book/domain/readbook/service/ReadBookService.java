package com.won.book.domain.readbook.service;

import com.won.book.converter.ReadBookConverter;
import com.won.book.domain.member.repository.MemberRepository;
import com.won.book.domain.readbook.entity.ReadBook;
import com.won.book.domain.readbook.dto.ReadBookDto;
import com.won.book.domain.readbook.repository.ReadBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReadBookService {

    private final ReadBookRepository readBookRepository;
    private final ReadBookConverter readBookConverter;

    private final MemberRepository memberRepository;

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
}
