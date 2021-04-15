package com.sda.company.service;

import com.sda.company.model.Company;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company create(Company company);

    String populate(List<Company> companies);

    List<Company> getAll();

    List<Company> getAllPaginated(Integer pageNumber, Integer pageSize, String sortBy);

    Company findByName(String name);

    void deleteCompanyById(Integer id);

    void deleteCompanyByRegistrationNumber(String registrationNumber);

}
