package com.won.bookdomain.repository;

import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.WantBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WantBookRepository extends JpaRepository<WantBook, Long> {

    List<WantBook> findAllByUser(User user);
    Optional<WantBook> findByIdAndUser(Long wantBookId, User user);
}
