package com.pandacreep.onlineshop.frontend;

import com.pandacreep.onlineshop.domain.basket.Basket;
import com.pandacreep.onlineshop.domain.basket.BasketService;
import com.pandacreep.onlineshop.domain.category.CategoryService;
import com.pandacreep.onlineshop.domain.exception.ResourceNotFoundException;
import com.pandacreep.onlineshop.domain.product.ProductDtoMvc;
import com.pandacreep.onlineshop.domain.product.ProductSearchForm;
import com.pandacreep.onlineshop.domain.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
@AllArgsConstructor
public class FrontendController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final PropertiesService propertiesService;
    private final BasketService basketService;
    private List<ProductDtoMvc> transferProductsDto;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/search")
    public String showSearch(Model model) {
        model.addAttribute("products", transferProductsDto);
        return "search";
    }

    @PostMapping("/search")
    public String showSearchResults(@Valid ProductSearchForm form,
                            BindingResult validationResult,
                            RedirectAttributes attributes) {
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            transferProductsDto.clear();
            return "redirect:/search";
        }
        List<ProductDtoMvc> productsDto = productService.getProducts();
        transferProductsDto = productService.filter(productsDto, form);
        return "redirect:/search";
    }

    @GetMapping("/search-mvc")
    public String showSearchMvc(Model model) {
        model.addAttribute("products", transferProductsDto);
        return "search-mvc";
    }

    @GetMapping("/categories")
    public String showCategories(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var categoriesDTO = categoryService.findAllCategories(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(categoriesDTO, propertiesService.getDefaultPageSize(), model, uri);
        return "categories";
    }

    @GetMapping("/categories/{id:\\d+?}")
    public String showCategoryProducts(@PathVariable int id,
                                       Model model,
                                       Pageable pageable,
                                       HttpServletRequest uriBuilder) {
        model.addAttribute("category", categoryService.getCategory(id));
        model.addAttribute("categoryId", id);
        var uri = uriBuilder.getRequestURI();
        var products = productService.getProductsByCategoryPage(id, pageable);
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);
        model.addAttribute("totalCount", products.getTotalElements());
        return "category";
    }

    @GetMapping("/products")
    public String showProducts(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var products = productService.getRestProductsPage(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);
        return "products";
    }

    @GetMapping("/products-mvc")
    public String showProductsMvc(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var productsDTO = productService.getProductsPage(pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(productsDTO, propertiesService.getDefaultPageSize(), model, uri);
        return "products-mvc";
    }

    @GetMapping("/products/{id:\\d+?}")
    public String showProduct(@PathVariable int id,
                                       Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "product";
    }

    @GetMapping("/basket")
    public String showBasket(Model model, Principal principal, HttpSession session) {
        model.addAttribute("email", principal.getName());
        return "basket";
    }

    @GetMapping("/order/{email}")
    public String showOrderForm(@PathVariable String email,
                                Model model) {
        model.addAttribute("email", email);
        Basket basket = basketService.getBasketOrNull(email);
        model.addAttribute("amount", basket.getTotalPrice());
        return "order";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }
}
