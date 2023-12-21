package com.won.bookdomain.repository;

import com.won.bookdomain.domain.WantBookReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WantBookReasonRepository extends JpaRepository<WantBookReason, Long> {
}
