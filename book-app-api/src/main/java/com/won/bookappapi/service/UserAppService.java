package com.won.bookappapi.service;

import com.won.bookappapi.api.request.UserCreateRequest;
import com.won.bookappapi.service.eventhandler.UserJoinEvent;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAppService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public Long join(UserCreateRequest createRequest) {
        User user = userRepository.save(
                User.createUser(
                        createRequest.getName(),
                        createRequest.getEmail(),
                        createRequest.getPassword(),
                        passwordEncoder));

        // 이메일 발송
        eventPublisher.publishEvent(new UserJoinEvent(user));

        return user.getId();
    }
}
