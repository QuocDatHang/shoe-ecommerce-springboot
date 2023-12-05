package com.example.shoeEcommerceSpringboot.repository;

import com.example.shoeEcommerceSpringboot.Model.Category;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
