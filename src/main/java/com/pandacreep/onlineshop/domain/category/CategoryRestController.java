package com.pandacreep.onlineshop.domain.category;

import com.pandacreep.onlineshop.domain.product.ProductDtoRest;
import com.pandacreep.onlineshop.domain.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public List<CategoryDTO> getCategories(Pageable pageable) {
        return categoryService.findAllCategories(pageable)
                .getContent();
    }

    @GetMapping("/{id:\\d+?}")
    public CategoryDTO getCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/{id:\\d+?}/products")
    public List<ProductDtoRest> getProducts(@PathVariable int id,
                                            Pageable pageable) {
        return productService.getProductsByCategoryPage(id, pageable).getContent();
    }
}
