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
}
