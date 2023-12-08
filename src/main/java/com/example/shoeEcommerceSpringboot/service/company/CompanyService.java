package com.example.shoeEcommerceSpringboot.service.company;

import com.example.shoeEcommerceSpringboot.Model.Company;
import com.example.shoeEcommerceSpringboot.repository.ICompanyRepository;
import com.example.shoeEcommerceSpringboot.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanyService implements IGeneralService<Company, Long> {
    @Autowired
    private ICompanyRepository companyRepository;
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return null;
    }

    @Override
    public void update(Company company) {

    }

    @Override
    public void create(Company company) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
