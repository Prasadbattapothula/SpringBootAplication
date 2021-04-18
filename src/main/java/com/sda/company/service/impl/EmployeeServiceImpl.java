package com.sda.company.service.impl;

import com.sda.company.exception.CompanyNotFoundException;
import com.sda.company.exception.EmployeeNotFoundException;
import com.sda.company.model.Company;
import com.sda.company.model.Employee;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.CompanyService;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final CompanyService companyService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findByFirstName(String name) {
        return employeeRepository.findByFirstName(name).orElseThrow(() -> new EmployeeNotFoundException("Employee with name " + name + " not found"));
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found.");
        }
    }

    @Override
    public Employee hire(Integer employeeId, Integer companyId) {
        Company company = companyService.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
//        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
//
//        employee.setCompany(company);
//        employee.setHired(true);
//
//        return employeeRepository.save(employee);

        //varianta mai rapida mai jos:

        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setCompany(company);
            employee.setHired(true);

            return employeeRepository.save(employee);
        }).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }


}
