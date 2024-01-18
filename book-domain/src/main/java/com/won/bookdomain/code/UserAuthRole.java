package com.won.bookdomain.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ;

    private final String description;
}
