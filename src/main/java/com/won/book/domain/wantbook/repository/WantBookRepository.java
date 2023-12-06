package com.won.book.domain.wantbook.repository;

import com.won.book.domain.member.Member;
import com.won.book.domain.wantbook.entity.WantBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WantBookRepository extends JpaRepository<WantBook, Long> {

    List<WantBook> findAllByMember(Member member);
}
