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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handlerMethodArgumentNotValidException = ", e);

        ExceptionCode exceptionCode = ExceptionCode.INVALID_PARAMETER;
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    String format = "%s : %s";
                    return String.format(format, error.getField(), error.getDefaultMessage());
                })
                .toList();

        String errorMessage = errorMessages.isEmpty() ? exceptionCode.getMessage() : errorMessages.toString();
        return new ResponseResult<>(HttpStatus.BAD_REQUEST, exceptionCode.getCode(), errorMessage);
    }
}
