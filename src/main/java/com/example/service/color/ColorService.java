package com.example.service.color;

import com.example.Model.Color;
import com.example.repository.IColorRepository;
import com.example.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Color> findById(Long id) {
        return Optional.empty();
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
