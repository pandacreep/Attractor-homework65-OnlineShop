package com.pandacreep.onlineshop.domain.exception;

public class OnlineshopInfoSuccessLoginException extends OnlineshopException {
    private String message;

    public OnlineshopInfoSuccessLoginException() {
        super();
    }

    public OnlineshopInfoSuccessLoginException(String message) {
        super(message);
        this.message = message;
    }
}
