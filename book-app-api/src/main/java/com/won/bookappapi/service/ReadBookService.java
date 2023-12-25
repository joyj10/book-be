package com.won.bookappapi.service;

import com.won.bookappapi.converter.ReadBookConverter;
import com.won.bookappapi.service.dto.ReadBookDto;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.repository.MemberRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
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
}
