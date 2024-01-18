package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.UserCreateRequest;
import com.won.bookappapi.service.UserAppService;
import com.won.bookcommon.response.ResponseResult;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "Login/Logout controller")
public class UserController {
    private final UserAppService userAppService;

    @PostMapping("/join")
    public ResponseResult<Long> join(@Valid @RequestBody UserCreateRequest createRequest) {
        Long result = userAppService.join(createRequest);
        return new ResponseResult<>(result);
    }
}
