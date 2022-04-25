package com.pandacreep.onlineshop.domain.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
    private int id;
    private String email;
    private String dateTime;
    private String orderReceiverName;
    private String phoneNumber;
    private String address;
    private String paymentType;
    private double amount;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .dateTime(order.getDateTime())
                .email(order.getEmail())
                .orderReceiverName(order.getOrderReceiverName())
                .phoneNumber(order.getPhoneNumber())
                .address(order.getAddress())
                .paymentType(order.getPaymentType())
                .amount(order.getAmount())
                .build();
    }
}
