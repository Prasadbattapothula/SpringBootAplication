package com.sda.company.controller;

import com.sda.company.model.Employee;
import com.sda.company.service.CompanyService;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@ControllerAdvice
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/findByName")
    public ResponseEntity<Employee> findByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam Integer id) {
        employeeService.deleteEmployeeById(id);
    }
}
