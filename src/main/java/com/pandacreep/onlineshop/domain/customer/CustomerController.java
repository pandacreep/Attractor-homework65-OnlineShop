package com.pandacreep.onlineshop.domain.customer;

import com.pandacreep.onlineshop.domain.exception.OnlineshopCustomerDontExistException;
import com.pandacreep.onlineshop.domain.exception.OnlineshopCustomerExistException;
import com.pandacreep.onlineshop.domain.exception.OnlineshopInfoSuccessLoginException;
import com.pandacreep.onlineshop.domain.exception.OnlineshopSuccessRegisterException;
import com.pandacreep.onlineshop.util.Captcha;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public String registerPage(@Valid CustomerRegisterForm form,
                               BindingResult validationResult,
                               RedirectAttributes attributes,
                               @RequestParam int result,
                               HttpSession session) throws OnlineshopCustomerExistException, OnlineshopSuccessRegisterException {
        attributes.addFlashAttribute("form", form);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        if(!customerService.isCaptchaCorrect(result, session)) {
            return "redirect:/register";
        }
        Customer customerSaved = customerService.save(form);
        throw new OnlineshopSuccessRegisterException(customerSaved);
    }

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model, HttpSession session) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new CustomerRegisterForm());
        }
        Captcha captcha = new Captcha();
        session.setAttribute("captcha", captcha);
        model.addAttribute("captcha", captcha);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/logout-page")
    public String pageLogoutCustomer() {
        return "logout-page";
    }

    @ExceptionHandler(OnlineshopCustomerExistException.class)
    private String handleCustomerExistException(OnlineshopCustomerExistException ex,
                                                Model model) {
        model.addAttribute("header", "Error");
        model.addAttribute("message", ex.getMessage());
        return "info";
    }

    @ExceptionHandler(OnlineshopCustomerDontExistException.class)
    private String handleCustomerDontExistException(OnlineshopCustomerDontExistException ex,
                                                    Model model) {
        model.addAttribute("header", "Error");
        model.addAttribute("message", ex.getMessage());
        return "info";
    }

    @ExceptionHandler(OnlineshopSuccessRegisterException.class)
    private String handleSuccessRegisterException(OnlineshopSuccessRegisterException ex,
                                                  Model model) {
        model.addAttribute("header", "Registration completed successfully");
        model.addAttribute("customer", ex.getCustomer());
        return "info-profile";
    }

    @ExceptionHandler(OnlineshopInfoSuccessLoginException.class)
    private String handleInfoSuccessLogin(OnlineshopInfoSuccessLoginException ex,
                                                  Model model) {
        model.addAttribute("header", "Info");
        model.addAttribute("message", ex.getMessage());
        return "info";
    }
}
