package com.won.bookappapi.api.controller;

import com.won.bookappapi.ControllerTestSupport;
import com.won.bookappapi.api.request.BookCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
class BookControllerTest extends ControllerTestSupport {

    @DisplayName("원하는 책을 키워드 검색으로 조회한다.")
    @Test
    @WithMockUser("테스트")
    void searchBooks() throws Exception {
        // given
        String keyword = "자바";

        // when // then
        mockMvc.perform(
                        get("/books")
                        .param("keyword", keyword)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.result").isArray());
    }

    @DisplayName("원하는 책의 키워드를 null로 전달 시 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void searchBooks_keyword_null() throws Exception {
        // given
        String keyword = null;

        // when // then
        mockMvc.perform(
                        get("/books")
                                .param("keyword", keyword)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("특정 책의 상세 정보를 조회한다.")
    @Test
    @WithMockUser("테스트")
    void getBook() throws Exception {
        // given
        // when // then
        mockMvc.perform(
                        get("/books/1")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"));
    }

    @DisplayName("저장된 책이 없는 경우 책을 저장한다.")
    @Test
    @WithMockUser("테스트")
    void save() throws Exception {
        // given
        BookCreateRequest createRequest = BookCreateRequest.builder()
                .title("java")
                .author("kim")
                .price(1000)
                .publisher("개발 출판사")
                .sort("IT")
                .build();

        // when // then
        mockMvc.perform(
                        post("/books").with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("책 저장 시 title 빈값 이면 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void save_title_blank() throws Exception {
        // given
        BookCreateRequest createRequest = BookCreateRequest.builder()
                .title("   ")
                .author("kim")
                .price(1000)
                .publisher("개발 출판사")
                .sort("IT")
                .build();

        // when // then
        mockMvc.perform(
                        post("/books").with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("책 저장 시 author 빈값 이면 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void save_author_blank() throws Exception {
        // given
        BookCreateRequest createRequest = BookCreateRequest.builder()
                .title("java")
                .author("  ")
                .price(1000)
                .publisher("개발 출판사")
                .sort("IT")
                .build();

        // when // then
        mockMvc.perform(
                        post("/books").with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("책 저장 시 sort 빈값 이면 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void save_sort_blank() throws Exception {
        // given
        BookCreateRequest createRequest = BookCreateRequest.builder()
                .title("java")
                .author("kim")
                .price(1000)
                .publisher("개발 출판사")
                .sort(null)
                .build();

        // when // then
        mockMvc.perform(
                        post("/books").with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @DisplayName("책 저장 시 price 음수 이면 예외가 발생한다.")
    @Test
    @WithMockUser("테스트")
    void save_price_not_positive() throws Exception {
        // given
        BookCreateRequest createRequest = BookCreateRequest.builder()
                .title("java")
                .author("kim")
                .price(-1000)
                .publisher("개발 출판사")
                .sort("IT")
                .build();

        // when // then
        mockMvc.perform(
                        post("/books").with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
