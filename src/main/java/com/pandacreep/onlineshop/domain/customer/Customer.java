package com.pandacreep.onlineshop.domain.customer;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Table(name = "customers")
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String email;

    @NotBlank
    @Size(min = 3, max = 128)
    @Column(length = 128)
    private String password;

    @NotBlank
    @Size(min = 3, max = 128)
    @Column(length = 128)
    private String fullname;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";

    public static Customer from(CustomerRegisterForm form) {
        return Customer.builder()
                .email(form.getEmail())
                .fullname(form.getName())
                .password(form.getPassword())
                .build();
    }
}
