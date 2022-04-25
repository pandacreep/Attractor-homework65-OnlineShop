package com.pandacreep.onlineshop.domain.product;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProductSearchForm {
    private String name = "";
    private String description = "";

    @NotNull(message = "Should not be blank")
    @DecimalMin(value = "0.0", message = "price must be >= 0.0")
    @DecimalMax(value = "100000.0", message = "price must be <= 100000.0")
    private double priceFrom = 0.0;

    @NotNull(message = "Should not be blank")
    @DecimalMin(value = "0.0", message = "price must be >= 0.0")
    @DecimalMax(value = "100000.0", message = "price must be <= 100000.0")
    private double priceTo = 0.0;

    private int category;
}
