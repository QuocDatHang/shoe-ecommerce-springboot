package com.example.shoeEcommerceSpringboot.controller.api;

import com.example.shoeEcommerceSpringboot.Model.Category;
import com.example.shoeEcommerceSpringboot.Model.Company;
import com.example.shoeEcommerceSpringboot.service.category.CategoryService;
import com.example.shoeEcommerceSpringboot.service.company.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryAPI {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<> (categories, HttpStatus.OK);
    }
}
