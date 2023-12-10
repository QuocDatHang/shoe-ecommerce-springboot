package com.example.service.category;

import com.example.Model.Category;
import com.example.repository.ICategoryRepository;
import com.example.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements IGeneralService<Category, Long> {

    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void create(Category category) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
