package com.won.bookappapi.service;

import com.won.bookappapi.api.request.UserCreateRequest;
import com.won.bookdomain.domain.User;
import com.won.bookdomain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class UserAppServiceTest {
    @Autowired
    private UserAppService userAppService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void join() {
        //given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("김회원")
                .email("test2@test.com")
                .password("test1234")
                .build();

        //when
        Long userId = userAppService.join(userCreateRequest);

        //then
        User findUser = userRepository.findById(userId).orElseThrow();
        then(findUser.getEmail()).isEqualTo(userCreateRequest.getEmail());
        then(findUser.getName()).isEqualTo(userCreateRequest.getName());
    }
}
