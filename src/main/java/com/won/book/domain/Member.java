package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Member
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Entity
@Getter @Setter
@Table(name = "member")
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "member")
    private List<ReadBook> readBooks = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<WantBook> wantBooks = new ArrayList<>();
}

