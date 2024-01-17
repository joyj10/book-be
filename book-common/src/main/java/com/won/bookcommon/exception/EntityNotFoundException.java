package com.won.bookcommon.exception;

import static com.won.bookcommon.exception.ExceptionCode.ENTITY_NOT_FOUND;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message, ENTITY_NOT_FOUND);
    }
}
