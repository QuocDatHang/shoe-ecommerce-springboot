package com.example.controller.api;

import com.example.Model.ProductImage;
import com.example.service.productImage.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/product-images")
public class ProductImageAPI {
    @Autowired
    private ProductImageService productImageService;

    @PostMapping
    public ProductImage upload(@RequestParam("image") MultipartFile image) throws IOException {
        return productImageService.saveImage(image);
    }
}
