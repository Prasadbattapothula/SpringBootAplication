package com.sda.company.controller;

import com.sda.company.components.CustomFakerCompany;
import com.sda.company.model.Company;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@ControllerAdvice
public class CompanyController {

    private final CustomFakerCompany customFakerCompany;
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService, CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    //the @RequestBody annotation allows us to retrieve the request’s body and automatically convert it to Java Object.
    //the @RequestBody annotation binds the HTTPRequest body to the domain object.
    //RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.

    @PostMapping("/create")
    public ResponseEntity<Company> create(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.create(company));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/getAllPaginated")
    public ResponseEntity<List<Company>> getAllPaginated(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.ok(companyService.getAllPaginated(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/findByName")
    public ResponseEntity<Company> findByName(@RequestParam String name) {
        return ResponseEntity.ok(companyService.findByName(name));
    }

    @GetMapping("/populate")
    public ResponseEntity<String> populate() {
        return ResponseEntity.ok(companyService.populate(customFakerCompany.createDummyCompanyList()));
    }

    @DeleteMapping("/delete")
    public void deleteById(@RequestParam Integer id) {
        companyService.deleteCompanyById(id);
    }

    @DeleteMapping("/deleteByRegNo")
    public void deleteByRegNo(@RequestParam String regNo) {
        companyService.deleteCompanyByRegistrationNumber(regNo);
    }
}
