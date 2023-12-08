package com.example.shoeEcommerceSpringboot.service.product;

import com.example.shoeEcommerceSpringboot.Model.Filter;
import com.example.shoeEcommerceSpringboot.Model.Product;
import com.example.shoeEcommerceSpringboot.Model.dto.FilterDTO;
import com.example.shoeEcommerceSpringboot.repository.IProductRepository;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductService implements IGeneralService<Product, Long> {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> filterProduct(FilterDTO filterDTO) {
        Filter filter = filterDTO.toFilter(filterDTO);
        return productRepository.filterProduct(filter);
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void create(Product product) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
