package com.pandacreep.onlineshop.domain.exception;

import com.pandacreep.onlineshop.domain.customer.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineshopSuccessRegisterException extends OnlineshopException{
    private Customer customer;
    public OnlineshopSuccessRegisterException() {
        super();
    }

    public OnlineshopSuccessRegisterException(Customer customer) {
        this.customer = customer;
    }
}
