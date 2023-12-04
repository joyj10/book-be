package com.won.book.domain.readbook;

import com.won.book.converter.ReadBookConverter;
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

    /**
     * 회원 읽은 책 전체 조회
     * @param memberId 회원 아이디
     * @return List<ReadBookDto>
     */
    @Transactional(readOnly = true)
    public List<ReadBookDto> getList(Long memberId) {
        List<ReadBook> readBooks = readBookRepository.findAllByMemberIdWithBook(memberId);
        return readBookConverter.convert(readBooks);
    }
}
