package com.won.bookcommon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // Common - 400 BAD_REQUEST
    BAD_REQUEST(400, "CB0001", "Bad Request"),
    INVALID_PARAMETER(400, "CB0002", "Invalid Input Parameter"),
    ENTITY_NOT_FOUND(400, "CB0003", "Entity Not Found"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
