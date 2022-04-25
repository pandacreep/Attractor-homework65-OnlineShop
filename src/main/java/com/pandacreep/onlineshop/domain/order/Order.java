package com.pandacreep.onlineshop.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 128)
    private String email;

    @Column(length = 128, name = "order_date")
    private String dateTime;

    @Column(length = 128, name = "fullname")
    private String orderReceiverName;

    @Column(length = 128, name = "phone")
    private String phoneNumber;

    @Column(length = 128)
    private String address;

    @Column(length = 128, name = "payment_type")
    private String paymentType;

    private double amount;

    public static Order from(OrderForm form) {
        return builder()
                .email(form.getEmail())
                .dateTime(form.getDateTime())
                .orderReceiverName(form.getOrderReceiverName())
                .phoneNumber(form.getPhoneNumber())
                .address(form.getAddress())
                .paymentType(form.getPaymentType())
                .amount(form.getAmount())
                .build();
    }
}
