package com.example.repository;

import com.example.Model.Cart;
import com.example.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByCustomer(Customer customer);

}
