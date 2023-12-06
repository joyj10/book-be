package com.won.book.domain.wantbook.repository;

import com.won.book.domain.wantbook.entity.WantBookReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WantBookReasonRepository extends JpaRepository<WantBookReason, Long> {
}
