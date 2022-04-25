package com.pandacreep.onlineshop.domain.basket;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/api/basket")
public class BasketRestController {
    private BasketService basketService;

    @GetMapping("/{email}")
    public ResponseEntity<Basket> getBasket(@PathVariable String email, HttpSession session) {
        Basket basket = new Basket();
        if (session != null) {
            var attr = session.getAttribute(Constants.BASKET_ID);
            if (attr == null) {
                basket = basketService.getBasketOrNull(email);
            } else {
                basket = (Basket) attr;
            }
        }
        basketService.setBasket(basket, email);
        return ResponseEntity.ok().body(basket);
    }

    @PostMapping("/{email}")
    public ResponseEntity<Basket> setBasket(@RequestBody Basket basket, @PathVariable String email) {
        basketService.setBasket(basket, email);
        return ResponseEntity.ok().body(basketService.getBasket(email));
    }

    @GetMapping("/{email}/clear")
    public ResponseEntity<Basket> clearBasket(@PathVariable String email, HttpSession session) {
        var basket = new Basket(new ArrayList<>(), 0);
        basketService.setBasket(basket, email);
        session.setAttribute(Constants.BASKET_ID, basket);
        return ResponseEntity.ok().body(basket);
    }

    @GetMapping("/{email}/add/{productId}")
    public ResponseEntity<Basket> addProductToBasket(@PathVariable String email,
                                                     @PathVariable int productId,
                                                     HttpSession session) {
        Basket basket = new Basket();
        if (session != null) {
            var attr = session.getAttribute(Constants.BASKET_ID);
            if (attr == null) {
                basket = basketService.getBasketOrNull(email);
            } else {
                basket = (Basket) attr;
            }
        }

        basket = basketService.addProduct(productId, basket);
        session.setAttribute(Constants.BASKET_ID, basket);
        basketService.setBasket(basket, email);
        return ResponseEntity.ok().body(basket);
    }

    @GetMapping("/{email}/delete/{productId}")
    public ResponseEntity<Basket> deleteProductFromBasket(@PathVariable String email,
                                                          @PathVariable int productId,
                                                          HttpSession session) {
        var basket = basketService.getBasketOrNull(email);
        basket = basketService.deleteProduct(productId, basket);
        session.setAttribute(Constants.BASKET_ID, basket);
        basketService.setBasket(basket, email);
        return ResponseEntity.ok().body(basket);
    }

    @GetMapping("/{email}/qty-decrease/{productId}")
    public ResponseEntity<Basket> quantityDecrease(@PathVariable String email,
                                                   @PathVariable int productId,
                                                   HttpSession session) {
        var basket = basketService.getBasketOrNull(email);
        basket = basketService.quantityDecrease(productId, basket);
        session.setAttribute(Constants.BASKET_ID, basket);
        basketService.setBasket(basket, email);
        return ResponseEntity.ok().body(basket);
    }

    @GetMapping("/{email}/qty-increase/{productId}")
    public ResponseEntity<Basket> quantityIncrease(@PathVariable String email,
                                                   @PathVariable int productId,
                                                   HttpSession session) {
        var basket = basketService.getBasketOrNull(email);
        basket = basketService.quantityIncrease(productId, basket);
        session.setAttribute(Constants.BASKET_ID, basket);
        basketService.setBasket(basket, email);
        return ResponseEntity.ok().body(basket);
    }
}
