package com.example.shoeEcommerceSpringboot.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        return new ResponseEntity<> (HttpStatus.OK);
    }
}
