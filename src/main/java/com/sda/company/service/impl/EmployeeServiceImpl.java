package com.sda.company.service.impl;

import com.sda.company.exception.EmployeeNotFoundException;
import com.sda.company.model.Employee;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
    public Employee findByName(String name) {
        return employeeRepository.findByName(name).orElseThrow(() -> new EmployeeNotFoundException("Employee with name " + name + " not found"));
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
}
