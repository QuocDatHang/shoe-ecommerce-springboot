package com.example.repository;

import com.example.Model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IColorRepository extends JpaRepository<Color, Long> {
}
