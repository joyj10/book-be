package com.won.bookappapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.bookappapi.api.request.ReadBookCreateRequest;
import com.won.bookappapi.service.ReadBookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReadBookController.class)
class ReadBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReadBookService readBookService;

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
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/read").with(csrf())
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
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/read").with(csrf())
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
                .rating(10)
                .reviews(List.of("재미 있는 책"))
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/read").with(csrf())
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
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/book/read").with(csrf())
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("CB0001"));
    }
}
