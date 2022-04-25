package com.pandacreep.onlineshop.domain.customer;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CustomerRegisterForm {
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well-formed email address")
    private String email = "";

    @Size(min=4, max=24, message = "name length must be >= 4 and <= 24")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "name should contain only letters")
    private String name = "";

    @Size(min=3, max=10, message = "password length must be >= 3 and <= 10")
    private String password = "";
}
