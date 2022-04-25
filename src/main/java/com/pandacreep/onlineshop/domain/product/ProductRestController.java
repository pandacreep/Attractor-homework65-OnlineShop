package com.pandacreep.onlineshop.domain.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDtoRest> getRestProducts(Pageable pageable) {
        return productService.getRestProductsPage(pageable)
                .getContent();
    }

    @GetMapping("/{id:\\d+?}")
    public ProductDtoRest getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @RequestMapping("/simple/{search}")
    public List<ProductDtoRest> getProductsFiltered(@PathVariable String search,
                                                    @RequestParam String name,
                                                    @RequestParam String description,
                                                    @RequestParam Double priceFrom,
                                                    @RequestParam Double priceTo,
                                                    @RequestParam String category,
                                                    @RequestParam int page,
                                                    @RequestParam int size) {
        var productsFiltered = productService.search(
                name, description, priceFrom, priceTo, category, page, size);
        return productsFiltered;
    }

    @RequestMapping("/{searchadvance}")
    public List<ProductDtoRest> getProductsFilteredSpec(@PathVariable String searchadvance,
                                                    @RequestParam String name,
                                                    @RequestParam String description,
                                                    @RequestParam Double priceFrom,
                                                    @RequestParam Double priceTo,
                                                    @RequestParam int category,
                                                    @RequestParam int page,
                                                    @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        var productsFiltered = productService.getProductsFiltered(
                        name, description, priceFrom, priceTo, category, pageable)
                .stream().collect(Collectors.toList());
        return productsFiltered;
    }
}
