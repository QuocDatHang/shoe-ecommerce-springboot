package com.example.service.order;

import com.example.Model.Cart;
import com.example.Model.Customer;
import com.example.Model.Order;
import com.example.Model.Status;
import com.example.Model.dto.OrderResDTO;
import com.example.repository.IOrderRepository;
import com.example.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements IGeneralService<Order, Long> {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void create(Order order) {
        orderRepository.save(order);
    }



    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
