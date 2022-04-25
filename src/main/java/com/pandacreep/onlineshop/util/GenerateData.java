package com.pandacreep.onlineshop.util;

import com.pandacreep.onlineshop.domain.category.CategoryRepository;
import com.pandacreep.onlineshop.domain.product.Product;
import com.pandacreep.onlineshop.domain.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenerateData {
    @Bean
    public CommandLineRunner init(CategoryRepository categoryRepository,
                                  ProductRepository productRepository) {
        return (args) -> {
            if (productRepository.count() != 0) {
                System.out.println("LOG: There are records in products table");
                return;
            }

            var productsFromFile = RandomDataGenerator.getProducts();
            for (RandomDataGenerator.ProductFromFile p : productsFromFile) {
                var category = categoryRepository.findById(p.getCategoryId()).get();
                var product = Product.builder()
                        .name(p.getName())
                        .image(p.getImage())
                        .qty(p.getQty())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .category(category)
                        .build();
                productRepository.save(product);
            }
        };
    }
}
