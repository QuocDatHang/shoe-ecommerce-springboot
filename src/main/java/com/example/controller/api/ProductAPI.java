package com.example.controller.api;

import com.example.Model.dto.FilterDTO;
import com.example.service.product.ProductService;
import com.example.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductAPI {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> getAllProducts(@RequestBody FilterDTO filterDTO, Pageable pageable) {
        pageable = PageRequest.of(filterDTO.getPage(), filterDTO.getSize(),
                filterDTO.getDirection().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, filterDTO.getSortField());
        Page<Product> products = productService.filterProduct(filterDTO, pageable);
        return new ResponseEntity<> (products, HttpStatus.OK);
    }
}
