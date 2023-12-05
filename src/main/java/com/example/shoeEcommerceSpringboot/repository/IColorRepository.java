package com.example.shoeEcommerceSpringboot.repository;

import com.example.shoeEcommerceSpringboot.Model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IColorRepository extends JpaRepository<Color, Long> {
}
