package com.won.bookdomain.repository;

import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.WantBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WantBookRepository extends JpaRepository<WantBook, Long> {
    @Query("select wb from WantBook wb join fetch wb.book join fetch wb.user where wb.user.id = :userId")
    List<WantBook> findAllWithBookByUserId(Long userId);
    Optional<WantBook> findByIdAndUser(Long wantBookId, User user);

    @Query("select wb from WantBook wb join fetch wb.book join fetch wb.wantBookReasons join fetch wb.user " +
            "where wb.id = :id and wb.user.id = :userId")
    Optional<WantBook> findWithReasonsByIdAndUserId(Long id, Long userId);
}
