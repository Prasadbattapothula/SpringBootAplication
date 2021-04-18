package com.sda.company.controller;

import com.sda.company.model.Company;
import com.sda.company.model.LoginForm;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HelloThymeleaf {

    private final CompanyService companyService;

    @Autowired
    public HelloThymeleaf(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("pass")) {
            return "home";
        } else {
            return "start";
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAllCompanies(Model model) {
        List<Company> companyList = companyService.getAll();

        model.addAttribute("list", companyList);

        // "list" de mai sus se leaga cu cel din tabela din companyTable
        //model.addAtribute este un map: cheia este cea cu care se leaga in frontend si value este din backend

        return "companyTable";
    }

}
