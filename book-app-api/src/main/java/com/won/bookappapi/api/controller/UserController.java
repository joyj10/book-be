package com.won.bookappapi.api.controller;

import com.won.bookappapi.api.request.UserCreateRequest;
import com.won.bookappapi.service.UserAppService;
import com.won.bookcommon.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "user controller", description = "사용자 관련API")
public class UserController {
    private final UserAppService userAppService;

    @PostMapping("/users/join")
    @Operation(summary = "사용자 회원 가입", description = "사용자 회원 가입을 합니다.")
    public ResponseResult<Long> join(@Valid @RequestBody UserCreateRequest createRequest) {
        Long result = userAppService.join(createRequest);
        return new ResponseResult<>(result);
    }
}
