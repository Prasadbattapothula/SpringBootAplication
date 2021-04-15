package com.sda.company.repository;

import com.sda.company.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {

    Optional<Company> findByName(String name);

    Optional<Company> findByRegistrationNumber(String registrationNumber);
}
