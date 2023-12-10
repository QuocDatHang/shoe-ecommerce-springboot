package com.example.service.price;

import com.example.Model.Price;
import com.example.repository.IPriceRepository;
import com.example.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Price> findById(Long id) {
        return Optional.empty();
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
