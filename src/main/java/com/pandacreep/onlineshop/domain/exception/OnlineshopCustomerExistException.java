package com.pandacreep.onlineshop.domain.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineshopCustomerExistException extends OnlineshopException{
    private String message;

    public OnlineshopCustomerExistException() {
        super();
    }

    public OnlineshopCustomerExistException(String message) {
        super(message);
        this.message = message;
    }
}
