package com.pandacreep.onlineshop.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class OrderForm {
    private String email;
    private String dateTime;
    private String orderReceiverName;
    private String phoneNumber;
    private String address;
    private String paymentType;
    private double amount;
}
