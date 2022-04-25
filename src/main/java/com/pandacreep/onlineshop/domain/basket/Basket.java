package com.pandacreep.onlineshop.domain.basket;

import com.pandacreep.onlineshop.domain.product.ProductDtoBasket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@RedisHash("Basket")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Basket implements Serializable {
    private List<ProductDtoBasket> products;
    private double totalPrice;

    public void calculateTotalPrice() {
        totalPrice = 0;
        products.forEach(p -> totalPrice += p.getPrice() * p.getQuantity());
    }
}
