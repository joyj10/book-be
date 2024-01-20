package com.won.bookappapi.service.eventhandler;

import com.won.bookdomain.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserJoinEvent {
    private final User user;
}
