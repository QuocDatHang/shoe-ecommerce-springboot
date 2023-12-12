package com.example.service.cart;

import com.example.Model.Cart;
import com.example.Model.Customer;
import com.example.repository.ICartRepository;
import com.example.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService implements IGeneralService<Cart, Long> {

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    public Optional<Cart> findByCustomer(Customer customer) {
        return cartRepository.findCartByCustomer(customer);
    }

    @Override
    public void update(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void create(Cart cart) {
        cartRepository.save(cart);
    }


    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
