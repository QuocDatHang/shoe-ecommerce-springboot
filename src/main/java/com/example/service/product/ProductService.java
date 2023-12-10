package com.example.service.product;

import com.example.Model.Filter;
import com.example.Model.dto.FilterDTO;
import com.example.service.IGeneralService;
import com.example.Model.Product;
import com.example.repository.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IGeneralService<Product, Long> {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> filterProduct(FilterDTO filterDTO, Pageable pageable) {
        Filter filter = filterDTO.toFilter(filterDTO);
        return productRepository.filterProduct(filter, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
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
