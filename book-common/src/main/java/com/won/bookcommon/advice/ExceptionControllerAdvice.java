package com.won.bookcommon.advice;

import com.won.bookcommon.exception.ExceptionCode;
import com.won.bookcommon.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseResult<Object> handleException(Exception e) {
        log.error("handleException", e);
        return new ResponseResult<>(ExceptionCode.INTERNAL_SERVER, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult<Object> handlerIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseResult<>(HttpStatus.BAD_REQUEST, ExceptionCode.INVALID_PARAMETER.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ExceptionCode exceptionCode = ExceptionCode.INVALID_PARAMETER;
        List<FieldError> errors = e.getBindingResult().getFieldErrors();

        String errorMessage = exceptionCode.getMessage();
        if (!errors.isEmpty()) {
            List<String> list = errors.stream().map(error -> error.getField() + " - " + error.getDefaultMessage()).toList();
            errorMessage = list.toString();
        }

        return new ResponseResult<>(HttpStatus.BAD_REQUEST, exceptionCode.getCode(), errorMessage);
    }
}
