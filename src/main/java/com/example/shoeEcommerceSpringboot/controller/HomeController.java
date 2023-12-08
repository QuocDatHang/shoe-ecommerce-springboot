package com.example.shoeEcommerceSpringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/products")
    public String homePage() {
        return "product";
    }

    @GetMapping("/carts")
    public String cartPage() {
        return "cart";
    }
}
