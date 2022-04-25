package com.pandacreep.onlineshop.util;

import com.pandacreep.onlineshop.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomDataGenerator {
    private static List<ProductFromFile> products;

    static {
        products = readProducts(Paths.get("data", "list-products"));
    }

    public static List<ProductFromFile> getProducts() {
        return products;
    }

    private static List<String> readStrings(Path filePath) {
        try (var lines = Files.lines(filePath)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static List<ProductFromFile> readProducts(Path filePath) {
        var lines = readStrings(filePath);
        return lines.stream()
                .map(RandomDataGenerator::toProduct)
                .collect(Collectors.toList());
    }

    private static ProductFromFile toProduct(String fromString) {
        var parts = fromString.split(";");
        return ProductFromFile.builder()
                .name(parts[0].strip())
                .image(parts[1].strip())
                .qty(Integer.parseInt(parts[2].strip()))
                .description(parts[3].strip())
                .price(Double.parseDouble(parts[4].strip()))
                .categoryId(Integer.parseInt(parts[5].strip()))
                .build();
    }

    @Data
    @Builder
    public static class ProductFromFile {
        public String name;
        public String image;
        public int qty;
        public String description;
        public double price;
        public int categoryId;
    }
}
