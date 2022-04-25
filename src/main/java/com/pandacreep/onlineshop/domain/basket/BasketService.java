package com.pandacreep.onlineshop.domain.basket;

import com.pandacreep.onlineshop.domain.product.ProductDtoBasket;
import com.pandacreep.onlineshop.domain.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BasketService {
    private RedisTemplate<String, Basket> redisTemplate;
    private ProductService productService;
    private final String region = "DMITRIY.PAK";

    public void setBasket(Basket basket, String email) {
        basket.calculateTotalPrice();
        String key = region + ":" + email;
        redisTemplate.opsForValue().set(key, basket);
    }

    public Basket getBasket(String email) {
        String key = region + ":" + email;
        return redisTemplate.opsForValue().get(key);
    }

    public Basket getBasketOrNull(String email) {
        var basket = getBasket(email);
        if (basket == null) {
            basket = new Basket(new ArrayList<>(), 0);
        }
        return basket;
    }

    public Basket addProduct(int productId, Basket basket) {
        boolean isProductExistInBasket = false;
        for (var product : basket.getProducts()) {
            if (product.getId() == productId) {
                isProductExistInBasket = true;
                product.setQuantity(product.getQuantity() + 1);
            }
        }
        if (!isProductExistInBasket) {
            var newProduct = productService.getProductForBasket(productId);
            basket.getProducts().add(newProduct);
        }
        return basket;
    }

    public Basket deleteProduct(int productId, Basket basket) {
        var products = basket.getProducts().stream()
                .filter(p -> p.getId() != productId)
                .collect(Collectors.toList());
        basket.setProducts(products);
        return basket;
    }

    public Basket quantityIncrease(int productId, Basket basket) {
        var products = basket.getProducts();
        for (var product : products) {
            if (product.getId() == productId) {
                product.setQuantity(product.getQuantity() + 1);
            }
        }
        return basket;
    }

    public Basket quantityDecrease(int productId, Basket basket) {
        var products = basket.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                products.get(i).setQuantity(products.get(i).getQuantity() - 1);
            }
        }
        removeZeroQuantity(basket, products);
        return basket;
    }

    private void removeZeroQuantity(Basket basket, List<ProductDtoBasket> productsToCheck) {
        var products = productsToCheck.stream()
                .filter(p -> p.getQuantity() > 0)
                .collect(Collectors.toList());
        basket.setProducts(products);
    }
}
