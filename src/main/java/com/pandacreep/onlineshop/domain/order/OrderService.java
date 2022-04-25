package com.pandacreep.onlineshop.domain.order;

import com.pandacreep.onlineshop.domain.basket.Constants;
import com.pandacreep.onlineshop.domain.basket.Basket;
import com.pandacreep.onlineshop.domain.basket.BasketService;
import com.pandacreep.onlineshop.domain.product.ProductDtoBasket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final BasketService basketService;

    public String add(OrderForm form, HttpSession session) {
        Order order = orderRepository.save(Order.from(form));
        var basket = basketService.getBasket(form.getEmail());
        List<ProductDtoBasket> products = basket.getProducts();
        for (int i = 0; i < products.size(); i++) {
            OrderItems item = OrderItems.builder()
                    .orderId(order.getId())
                    .productId(products.get(i).getId())
                    .qty(products.get(i).getQuantity())
                    .build();
            orderItemsRepository.save(item);
        }
        String email = form.getEmail();
        Basket basketEmpty = new Basket(new ArrayList<>(), 0);
        basketService.setBasket(basketEmpty, email);
        session.setAttribute(Constants.BASKET_ID, basketEmpty);
        return "/";
    }

    public List<OrderDto> findAll() {
        var orders = orderRepository.findAll()
                .stream()
                .map(o -> OrderDto.from(o))
                .collect(Collectors.toList());
        return orders;
    }
}
