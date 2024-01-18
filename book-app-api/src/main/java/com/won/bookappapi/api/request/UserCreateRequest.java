package com.won.bookappapi.api.request;

import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@ApiModel(value = "사용자 회원 가입 Request Model")
public class UserCreateRequest {
    @NotEmpty(message = "필수값 : 이름")
    private String name;

    @Email(message = "잘 못된 이메일 형식")
    private String email;

    @NotEmpty(message = "필수값 : 비밀번호")
    @Length(min = 8, max = 12, message = "비밀번호 : 8자 이상 12자 이하로 입력")
    private String password;

    @Builder
    public UserCreateRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
