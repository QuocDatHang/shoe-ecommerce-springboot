package com.example.controller.api;

import com.example.Model.Company;
import com.example.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyAPI {
    @Autowired
    private CompanyService companyService;
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Company> companies = companyService.findAll();
        return new ResponseEntity<> (companies, HttpStatus.OK);
    }
}
