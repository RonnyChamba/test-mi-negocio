package com.test.alquimiasoft.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String detail) {
        super(detail);
    }

}
