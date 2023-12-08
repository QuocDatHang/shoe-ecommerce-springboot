package com.example.shoeEcommerceSpringboot.service.color;

import com.example.shoeEcommerceSpringboot.Model.Color;
import com.example.shoeEcommerceSpringboot.repository.IColorRepository;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ColorService implements IGeneralService<Color, Long> {
    @Autowired
    private IColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findById(Long id) {
        return null;
    }

    @Override
    public void update(Color color) {

    }

    @Override
    public void create(Color color) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
