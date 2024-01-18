package com.won.bookappapi.service;

import com.won.bookappapi.api.request.UserCreateRequest;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAppService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Long join(UserCreateRequest createRequest) {
        User user = User.createUser(
                createRequest.getName(),
                createRequest.getEmail(),
                createRequest.getPassword(),
                passwordEncoder);

        return userRepository.save(user).getId();
    }
}
