package com.won.bookappapi.api.controller;

import com.won.bookappapi.ControllerTestSupport;
import com.won.bookappapi.api.request.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends ControllerTestSupport {

    @DisplayName("사용자 정보를 정상적으로 입력하면, 회원 가입이 성공한다.")
    @Test
    @WithAnonymousUser
    void join() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("박자바")
                .email("java@test.com")
                .password("12345678")
                .build();

        // when // then
        mockMvc.perform(
                post("/join").with(csrf())
                        .content(objectMapper.writeValueAsString(userCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("사용자 회의 가입 시 메일 형식이 정상적이지 않은 경우 예외가 발생한다.")
    @Test
    @WithAnonymousUser
    void join_email_invalid() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("박자바")
                .email("javatest.com")
                .password("12345678")
                .build();

        // when // then
        mockMvc.perform(
                        post("/join").with(csrf())
                                .content(objectMapper.writeValueAsString(userCreateRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("CB0001"));
    }

    @DisplayName("사용자 회의 가입 시 패드워드가 8 ~ 12 사이가 아닌 경우 예외가 발생한다.")
    @Test
    @WithAnonymousUser
    void join_password_invalid() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("박자바")
                .email("java@test.com")
                .password("1234")
                .build();

        // when // then
        mockMvc.perform(
                        post("/join").with(csrf())
                                .content(objectMapper.writeValueAsString(userCreateRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("CB0001"));
    }
}
