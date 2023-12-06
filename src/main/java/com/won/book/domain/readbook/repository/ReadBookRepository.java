package com.won.book.domain.readbook.repository;

import com.won.book.domain.member.Member;
import com.won.book.domain.readbook.entity.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadBookRepository extends JpaRepository<ReadBook, Long> {
    List<ReadBook> findAllByMember(Member member);
}
