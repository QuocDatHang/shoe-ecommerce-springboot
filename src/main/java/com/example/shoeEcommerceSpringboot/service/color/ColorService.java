package com.example.shoeEcommerceSpringboot.service.color;

import com.example.shoeEcommerceSpringboot.Model.Color;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ColorService implements IGeneralService<Color, Long> {
    @Override
    public List<Color> findAll() {
        return null;
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
