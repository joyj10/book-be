package com.won.bookappapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.bookappapi.api.controller.ReadBookController;
import com.won.bookappapi.service.BookService;
import com.won.bookappapi.service.ReadBookContentService;
import com.won.bookappapi.service.ReadBookService;
import com.won.bookappapi.service.UserAppService;
import com.won.bookdomain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        ReadBookController.class
})
public class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ReadBookService readBookService;

    @MockBean
    protected ReadBookContentService readBookContentService;

    @MockBean
    protected BookService bookService;

    @MockBean
    protected UserAppService userAppService;

    @MockBean
    protected UserRepository userRepository;
}
