package com.pandacreep.onlineshop.domain.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/addOrder")
    public String addOrder(OrderForm orderForm, HttpSession session) {
        orderService.add(orderForm, session);
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String showOrders(Model model, Principal principal) {
        model.addAttribute("email", principal.getName());
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }
}
