package com.example.repository;

import com.example.Model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPriceRepository extends JpaRepository<Price, Long> {

}
