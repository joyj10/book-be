package com.won.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * BaseController
 * <pre>
 * Describe here
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 * lia.jung, 1.0, 2022/09/11 초기작성
 * </pre>
 *
 * @author lia.jung
 * @version 1.0,
 */

@Controller
public class BaseController {
    @GetMapping("/")
    public String root() {
        return "index";
    }
}
