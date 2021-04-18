package com.sda.company.controller;

import com.sda.company.model.Employee;
import com.sda.company.service.EmployeeService;
import com.sun.istack.NotNull;
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

    @GetMapping("/findByFirstName")
    public ResponseEntity<Employee> findByFirstName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.findByFirstName(name));
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam Integer id) {
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/hire")
    public ResponseEntity<Employee> hireEmployee(@RequestParam @NotNull Integer employeeId,
                                                 @RequestParam @NotNull Integer companyId) {
        return ResponseEntity.ok(employeeService.hire(employeeId, companyId));
    }

}
