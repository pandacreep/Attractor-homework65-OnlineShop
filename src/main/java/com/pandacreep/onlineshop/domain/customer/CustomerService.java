package com.pandacreep.onlineshop.domain.customer;

import com.pandacreep.onlineshop.domain.exception.OnlineshopCustomerDontExistException;
import com.pandacreep.onlineshop.domain.exception.OnlineshopCustomerExistException;
import com.pandacreep.onlineshop.domain.exception.ResourceNotFoundException;
import com.pandacreep.onlineshop.util.Captcha;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    public Customer save(CustomerRegisterForm form) throws OnlineshopCustomerExistException {
        if (isEmailExist(form.getEmail())) {
            String message = "Email " + form.getEmail() + " already exist";
            throw new OnlineshopCustomerExistException(message);
        }
        var customer = Customer.builder()
                .email(form.getEmail())
                .fullname(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();
        return  customerRepository.save(customer);
    }

    private boolean isEmailExist(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            return false;
        }
        return true;
    }

    public Customer getById(int id) {
        return customerRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("customer", id));
    }

    public Customer check(String email, String password) throws OnlineshopCustomerDontExistException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        String message = "Credentials are wrong";
        throw new OnlineshopCustomerDontExistException(message);
    }

    public boolean isCaptchaCorrect(int result, HttpSession session) {
        Captcha captcha = (Captcha) session.getAttribute("captcha");
        if (captcha.getResult() == result)
            return true;
        return false;
    }
}
