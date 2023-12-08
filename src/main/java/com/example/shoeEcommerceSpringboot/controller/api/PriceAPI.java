package com.example.shoeEcommerceSpringboot.controller.api;

import com.example.shoeEcommerceSpringboot.Model.Price;
import com.example.shoeEcommerceSpringboot.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/prices")
public class PriceAPI {
    @Autowired
    private PriceService priceService;
    @GetMapping
    public ResponseEntity<?> getAllPrices() {
        List<Price> prices = priceService.findAll();
        return new ResponseEntity<> (prices, HttpStatus.OK);
    }
}
