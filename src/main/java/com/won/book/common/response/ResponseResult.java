package com.won.book.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseResult<T> {
    private int code;
    private int status;
    private String message;
    private T result;

    public ResponseResult(T result) {
        this.status = HttpStatus.OK.value();
        this.message = "success";
        this.result = result;
    }

    public ResponseResult(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }

    public ResponseResult(HttpStatus httpStatus, int code, String message) {
        this.status = httpStatus.value();
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
