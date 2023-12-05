package com.example.shoeEcommerceSpringboot.service.customer;

import com.example.shoeEcommerceSpringboot.Model.Customer;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService implements IGeneralService<Customer, Long> {
    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
