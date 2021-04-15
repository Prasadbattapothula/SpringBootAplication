package com.sda.company.exception;

import com.sda.company.model.Employee;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }


}
