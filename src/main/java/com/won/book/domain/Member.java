package com.won.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseDateEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<ReadBook> readBooks = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<WantBook> wantBooks = new ArrayList<>();
}

