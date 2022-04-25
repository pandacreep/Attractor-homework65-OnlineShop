package com.pandacreep.onlineshop.domain.product;

import com.pandacreep.onlineshop.domain.exception.ResourceNotFoundException;
import com.pandacreep.onlineshop.util.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    public Page<ProductDtoRest> getProductsByCategoryPage(int id, Pageable pageable) {
        var products = productRepository.findAllByCategoryId(id, pageable);
        return products.map(ProductDtoRest::from);
    }

    public Page<ProductDtoRest> getRestProductsPage(Pageable pageable) {
        var products = productRepository.findAll(pageable)
                .map(ProductDtoRest::from);
        return products;
    }

    public ProductDtoRest getProduct(int id) {
        var product = productRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("product", id));
        return ProductDtoRest.from(product);
    }

    public ProductDtoBasket getProductForBasket(int id) {
        var product = productRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("product", id));
        return ProductDtoBasket.from(product);
    }

    public List<ProductDtoRest> getProductsRest() {
        var productsDto = productRepository.findAll()
                .stream()
                .map(ProductDtoRest::from)
                .collect(Collectors.toList());
        return productsDto;
    }

    public Page<ProductDtoMvc> getProductsPage(Pageable pageable) {
        var productsDto = productRepository.findAll(pageable)
                .map(ProductDtoMvc::from);
        return productsDto;
    }

    public List<ProductDtoMvc> getProducts() {
        var productsDto = productRepository.findAll()
                .stream()
                .map(ProductDtoMvc::from)
                .collect(Collectors.toList());
        return productsDto;
    }

    public List<ProductDtoMvc> filter(List<ProductDtoMvc> products,
                                      ProductSearchForm form) {
        List<ProductDtoMvc> productsFiltered = products;
        if (form.getCategory() != -1) {
            productsFiltered = productsFiltered.stream()
                    .filter(product -> product.getCategory().getName().equals(form.getCategory()))
                    .collect(Collectors.toList());
        }
        productsFiltered = productsFiltered
                .stream()
                .filter(product -> product.getPrice() >= form.getPriceFrom())
                .filter(product -> product.getPrice() <= form.getPriceTo())
                .collect(Collectors.toList());
        if (!form.getName().isBlank()) {
            productsFiltered = productsFiltered.stream()
                    .filter(product -> product.getName()
                            .toLowerCase()
                            .contains(form.getName().toLowerCase()))
                    .collect(Collectors.toList());
        }
        return productsFiltered;
    }

    public List<ProductDtoRest> search(String name,
                                       String description,
                                       Double priceFrom,
                                       Double priceTo,
                                       String category,
                                       int page,
                                       int size) {
        List<ProductDtoRest> productsFiltered = getProductsRest();
        if (!category.isBlank()) {
            productsFiltered = productsFiltered.stream()
                    .filter(product -> product.getCategory().equals(category))
                    .collect(Collectors.toList());
        }
        productsFiltered = productsFiltered
                .stream()
                .filter(product -> product.getPrice() >= priceFrom)
                .filter(product -> product.getPrice() <= priceTo)
                .collect(Collectors.toList());
        if (!name.isBlank()) {
            productsFiltered = productsFiltered.stream()
                    .filter(product -> product.getName()
                            .toLowerCase()
                            .contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (!description.isBlank()) {
            productsFiltered = productsFiltered.stream()
                    .filter(product -> product.getDescription()
                            .toLowerCase()
                            .contains(description.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return makePage(productsFiltered, page, size);
    }

    public List<ProductDtoRest> makePage(List<ProductDtoRest> products,
                                         int page, int size) {
        List<ProductDtoRest> listPaged = new ArrayList<>();
        int indexStart = page * size;
        int indexEnd = indexStart + size - 1;
        for (int i = 0; i < products.size(); i++) {
            if (i >= indexStart && i <= indexEnd) {
                listPaged.add(products.get(i));
            }
        }
        return listPaged;
    }

    public Page<ProductDtoRest> getProductsFiltered(String name,
                                                   String description,
                                                   Double priceFrom,
                                                   Double priceTo,
                                                   Integer category,
                                                   Pageable pageable) {
        ProductSpecification priceFromFilter =
                new ProductSpecification(new SearchCriteria("price", ">", priceFrom));
        ProductSpecification priceToFilter =
                new ProductSpecification(new SearchCriteria("price", "<", priceTo));
        ProductSpecification nameFilter =
                new ProductSpecification(new SearchCriteria("name", ":", name));
        ProductSpecification descFilter =
                new ProductSpecification(new SearchCriteria("description", ":", description));
        ProductSpecification categoryFilter =
                new ProductSpecification(new SearchCriteria("category", ":", category));
        Page<Product> products;
        if (category == -1) {
            products = productSearchRepository.findAll(Specification.where(priceFromFilter).
                    and(priceToFilter).and(nameFilter).and(descFilter), pageable);
        } else {
            products = productSearchRepository.findAll(Specification.where(priceFromFilter).
                    and(priceToFilter).and(nameFilter).and(descFilter).and(categoryFilter), pageable);
        }
        return products.map(ProductDtoRest::from);
    }
}
