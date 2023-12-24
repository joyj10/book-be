package com.won.bookdomain.repository;

import com.won.bookdomain.domain.Member;
import com.won.bookdomain.domain.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadBookRepository extends JpaRepository<ReadBook, Long> {
    List<ReadBook> findAllByMember(Member member);
    Optional<ReadBook> findByIdAndMember(Long readBookId, Member member);
}
