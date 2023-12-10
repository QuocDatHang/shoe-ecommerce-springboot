package com.example.service.customer;

import com.example.Model.Customer;
import com.example.repository.ICustomerRepository;
import com.example.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements IGeneralService<Customer, Long> {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
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
