package com.example.shoeEcommerceSpringboot.service.price;

import com.example.shoeEcommerceSpringboot.Model.Price;
import com.example.shoeEcommerceSpringboot.repository.IPriceRepository;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PriceService implements IGeneralService<Price, Long> {
    @Autowired
    private IPriceRepository priceRepository;
    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Override
    public Price findById(Long id) {
        return null;
    }

    @Override
    public void update(Price price) {

    }

    @Override
    public void create(Price price) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
