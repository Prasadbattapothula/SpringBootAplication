package com.sda.company.components;

import com.github.javafaker.Faker;
import com.sda.company.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CustomFakerCompany {
    public List<Company> createDummyCompanyList() {
        Faker faker = new Faker();
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setAddress(faker.address().fullAddress());
            company.setPhoneNumber(faker.phoneNumber().phoneNumber());
            company.setEmail(faker.bothify("?????##@yahoo.com"));
            company.setRegistrationNumber(String.valueOf(faker.number().randomNumber(11, true)));

            companies.add(company);
        }
        return companies;
    }
}
