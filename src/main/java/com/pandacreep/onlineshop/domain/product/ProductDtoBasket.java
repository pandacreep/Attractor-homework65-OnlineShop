package com.pandacreep.onlineshop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoBasket implements Serializable {
    private int id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int quantity;

    public static ProductDtoBasket from(Product product) {
        return ProductDtoBasket.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(1)
                .build();
    }
}
