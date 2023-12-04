package com.won.book.domain.readbook;

import com.won.book.domain.readbook.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadBookRepository extends JpaRepository<ReadBook, Long> {
    @Query("SELECT r FROM ReadBook r JOIN FETCH r.book WHERE r.member.id = :memberId AND r.deleteYn = 'N'")
    List<ReadBook> findAllByMemberIdWithBook(Long memberId);
}
