package com.example.shoeEcommerceSpringboot.controller.api;

import com.example.shoeEcommerceSpringboot.Model.Filter;
import com.example.shoeEcommerceSpringboot.Model.Product;
import com.example.shoeEcommerceSpringboot.Model.dto.FilterDTO;
import com.example.shoeEcommerceSpringboot.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> getAllProducts(@RequestBody FilterDTO filterDTO) {
        List<Product> products = productService.filterProduct(filterDTO);
        return new ResponseEntity<> (products, HttpStatus.OK);
    }
}
