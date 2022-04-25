package com.pandacreep.onlineshop.domain.exception;

public class OnlineshopCustomerDontExistException extends OnlineshopException{
    private String message;

    public OnlineshopCustomerDontExistException() {
    }

    public OnlineshopCustomerDontExistException(String message) {
        super(message);
        this.message = message;
    }
}
