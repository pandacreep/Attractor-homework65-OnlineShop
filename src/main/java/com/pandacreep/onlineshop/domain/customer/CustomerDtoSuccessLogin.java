package com.pandacreep.onlineshop.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoSuccessLogin {
    private int id;
    private String email;
    private String password;
    private String fullname;

    public static CustomerDtoSuccessLogin from(Customer customer) {
        return CustomerDtoSuccessLogin.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .fullname(customer.getFullname())
                .build();
    }
}
