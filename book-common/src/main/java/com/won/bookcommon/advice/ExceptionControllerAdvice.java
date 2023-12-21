package com.won.bookcommon.advice;

import com.won.bookcommon.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult<Object> handlerIllegalArgumentException(IllegalArgumentException e) {
        log.error("[Controller error]", e);
        return new ResponseResult<>(HttpStatus.BAD_REQUEST, 40000, e.getMessage());
    }
}
