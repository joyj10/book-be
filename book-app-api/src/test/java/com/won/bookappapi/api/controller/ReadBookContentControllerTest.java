package com.won.bookappapi.api.controller;

import com.won.bookappapi.ControllerTestSupport;
import com.won.bookappapi.api.request.ReadBookContentCreateRequest;
import com.won.bookappapi.api.request.ReadBookContentUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReadBookContentController.class)
class ReadBookContentControllerTest extends ControllerTestSupport {

    @DisplayName("읽은 책에 저장된 좋은 글귀 리스트를 조회한다.")
    @Test
    @WithMockUser(username = "테스트")
    void getContents() throws Exception {
        // given
        // when // then
        mockMvc.perform(
                        get("/books/read/contents")
                                .param("readBookId", "1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.result").isArray());
    }

    @DisplayName("읽은 책의 좋은 글귀를 추가 저장한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save() throws Exception {
        // given
        ReadBookContentCreateRequest request = ReadBookContentCreateRequest.builder()
                .readBookId(1L)
                .contents(
                        List.of("좋은 글귀1", "좋은 글귀2")
                )
                .build();

        // when // then
        mockMvc.perform(
                post("/books/read/contents").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("읽은 책의 좋은 글귀 리스트가 빈값인 경우 예외가 발생한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save_fail_contents_empty() throws Exception {
        // given
        ReadBookContentCreateRequest request = ReadBookContentCreateRequest.builder()
                .readBookId(1L)
                .contents(List.of())
                .build();

        // when // then
        mockMvc.perform(
                post("/books/read/contents").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("읽은 책 아이디가 빈값인 경우 예외가 발생한다.")
    @Test
    @WithMockUser(username = "테스트")
    void save_fail_readBookId_null() throws Exception {
        // given
        ReadBookContentCreateRequest request = ReadBookContentCreateRequest.builder()
                .readBookId(null)
                .contents(List.of("좋은 글귀"))
                .build();

        // when // then
        mockMvc.perform(
                post("/books/read/contents").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("읽은 책의 좋은 글귀를 수정한다.")
    @Test
    @WithMockUser(username = "테스트")
    void update() throws Exception {
        // given
        String content = "좋은 글귀-변경";

        // when // then
        mockMvc.perform(
                patch("/books/read/contents/1").with(csrf())
                        .content(objectMapper.writeValueAsString(content))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @DisplayName("읽은 책의 좋은 글귀가 비어 있는 경우 예외가 발생한다.")
    @Test
    @WithMockUser(username = "테스트")
    void update_fail_content_empty() throws Exception {
        // given
        ReadBookContentUpdateRequest updateRequest = ReadBookContentUpdateRequest.builder()
                .content("    ")
                .build();

        // when // then
        mockMvc.perform(
                patch("/books/read/contents/1").with(csrf())
                        .content(objectMapper.writeValueAsString(updateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("읽은 책의 좋은 글귀를 삭제한다.")
    @Test
    @WithMockUser(username = "테스트")
    void deleteContent() throws Exception {
        // given
        // when // then
        mockMvc.perform(
                        delete("/books/read/contents/1").with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.result").isBoolean());
    }
}
