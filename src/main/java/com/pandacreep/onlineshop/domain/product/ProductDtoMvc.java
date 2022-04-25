package com.pandacreep.onlineshop.domain.product;

import com.pandacreep.onlineshop.domain.category.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDtoMvc {
    private int id;
    private String name;
    private String image;
    private int qty;
    private String description;
    private double price;
    private Category category;

    public static ProductDtoMvc from(Product product) {
        return builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .qty(product.getQty())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }
}
