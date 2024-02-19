package com.won.bookappapi.api.controller;

import com.won.bookappapi.ControllerTestSupport;
import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.api.request.ReadBookUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReadBookController.class)
class ReadBookControllerTest extends ControllerTestSupport {

    @DisplayName("회원의 전체 읽은 책을 조회한다.")
    @Test
    @WithMockUser(username = "테스트")
    void getReadBooks() throws Exception {
        // given
        // when // then
        mockMvc.perform(
                        get("/v1/books/read")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.result").isArray());
    }

    @DisplayName("읽은 책의 상세 데이터를 조회한다.")
    @Test
    @WithMockUser(username = "테스트")
    void getReadBook() throws Exception {
        // given
        // when // then
        mockMvc.perform(
                get("/v1/books/read/1")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"));
    }

    @DisplayName("읽은 책을 저장한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save() throws Exception {
        // given
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(1L)
                .readAt("2024-02-01")
                .rating(5)
                .reviews(List.of("재미 있는 책"))
                .build();

        // when // then
        mockMvc.perform(post("/v1/books/read").with(csrf())
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("읽은 책 저장 시 읽은 날 데이터가 빈값인 경우 예외가 발생한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save_invalid_readAt() throws Exception {
        // given
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(1L)
                .readAt("")
                .rating(5)
                .reviews(List.of("재미 있는 책"))
                .build();

        // when // then
        mockMvc.perform(post("/v1/books/read").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("CB0001"));
    }

    @DisplayName("읽은 책 저장 시 평점이 범위에서 벗어나는 경우 예외가 발생한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save_invalid_rating() throws Exception {
        // given
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(1L)
                .readAt("2024-02-01")
                .rating(6)
                .reviews(List.of("재미 있는 책"))
                .build();

        // when // then
        mockMvc.perform(post("/v1/books/read").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("CB0001"));
    }

    @DisplayName("읽은 책 저장 시 bookId 값은 양수여야 한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save_invalid_bookId() throws Exception {
        // given
        ReadBookCreateRequest request = ReadBookCreateRequest.builder()
                .bookId(-1L)
                .readAt("2024-02-01")
                .rating(3)
                .reviews(List.of("재미 있는 책"))
                .build();

        // when // then
        mockMvc.perform(post("/v1/books/read").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("CB0001"));
    }

    @DisplayName("읽은 책의 데이터를 수정한다.")
    @Test
    @WithMockUser("테스트")
    void update() throws Exception {
        // given
        ReadBookUpdateRequest request = ReadBookUpdateRequest.builder()
                .readAt("2024-01-01")
                .rating(4)
                .build();

        // when // then
        mockMvc.perform(patch("/v1/books/read/1").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("읽은 책 수정 시 읽은 날이 빈값이면 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void update_invalid_readAt() throws Exception {
        // given
        ReadBookUpdateRequest request = ReadBookUpdateRequest.builder()
                .readAt("")
                .rating(4)
                .build();

        // when // then
        mockMvc.perform(patch("/v1/books/read/1").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("CB0001"));
    }

    @DisplayName("읽은 책 수정 시 평점이 정해진 범위에서 벗어나면 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void update_invalid_rating() throws Exception {
        // given
        ReadBookUpdateRequest request = ReadBookUpdateRequest.builder()
                .readAt("2024-01-01")
                .rating(6)
                .build();

        // when // then
        mockMvc.perform(patch("/v1/books/read/1").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("CB0001"));
    }

    @DisplayName("읽은 책을 삭제할 수 있다.")
    @Test
    @WithMockUser("테스트")
    void DeleteReadBook() throws Exception {
        // given // when // then
        mockMvc.perform(delete("/v1/books/read/1").with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"));
    }

    @DisplayName("")
    @Test
    @WithMockUser("테스트")
    void saveRepeat() {
        // given
        // when
        // then
    }

    @DisplayName("")
    @Test
    @WithMockUser("테스트")
    void getReadBookOfMonth() {
        // given
        // when
        // then
    }

    @DisplayName("")
    @Test
    @WithMockUser("테스트")
    void getReadBookOfYear() {
        // given
        // when
        // then
    }
}
