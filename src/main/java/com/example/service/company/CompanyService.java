package com.example.service.company;

import com.example.Model.Company;
import com.example.repository.ICompanyRepository;
import com.example.service.IGeneralService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Company> findById(Long id) {
        return Optional.empty();
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
