package com.sda.company.service.impl;

import com.sda.company.exception.CompanyNotFoundException;
import com.sda.company.model.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public String populate(List<Company> companies) {
        List<Company> results = (List<Company>) companyRepository.saveAll(companies);
        if (results.isEmpty()) {
            return "There is a problem";
        } else {
            return "The list has been created";
        }
    }

    @Override
    public List<Company> getAll() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public List<Company> getAllPaginated(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Company> companyPage = companyRepository.findAll(pageable);
        return companyPage.getContent();
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new CompanyNotFoundException("Company with name " + name + " not found"));
    }

    @Override
    public void deleteCompanyById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            companyRepository.deleteById(id);
        } else {
            throw new CompanyNotFoundException("Company with id " + id + " not found");
        }
    }

    @Override
    public void deleteCompanyByRegistrationNumber(String registrationNumber) {
        Optional<Company> company = companyRepository.findByRegistrationNumber(registrationNumber);

        if(company.isPresent()) {
            companyRepository.deleteById(company.get().getId());
        }else {
            throw new CompanyNotFoundException("Company with registration number " + registrationNumber + " not found");
        }
    }
}
