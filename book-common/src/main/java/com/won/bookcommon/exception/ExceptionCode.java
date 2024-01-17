package com.won.bookcommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // Common - 400 BAD_REQUEST
    INVALID_PARAMETER(BAD_REQUEST, "CB0001", "Invalid Input Parameter"),
    ENTITY_NOT_FOUND(BAD_REQUEST, "CB0002", "Entity Not Found"),

    // Common - 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER(INTERNAL_SERVER_ERROR, "CI0001", "Internal Server Error")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
