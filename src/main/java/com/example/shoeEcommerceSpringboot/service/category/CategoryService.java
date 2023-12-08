package com.example.shoeEcommerceSpringboot.service.category;

import com.example.shoeEcommerceSpringboot.Model.Category;
import com.example.shoeEcommerceSpringboot.repository.ICategoryRepository;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Category findById(Long id) {
        return null;
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
