package com.example.shoeEcommerceSpringboot.repository;

import com.example.shoeEcommerceSpringboot.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
}
