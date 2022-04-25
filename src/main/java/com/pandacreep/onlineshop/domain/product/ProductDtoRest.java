package com.pandacreep.onlineshop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductDtoRest {
    private int id;
    private String name;
    private String image;
    private String description;
    private double price;
    private String category;

    public static ProductDtoRest from(Product product) {
        return builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .build();
    }
}

