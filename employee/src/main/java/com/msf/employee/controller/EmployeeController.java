package com.msf.employee.controller;


import com.msf.employee.request.AddEmployeeRequest;
import com.msf.employee.response.EmployeeTaxDetailsResponse;
import com.msf.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody AddEmployeeRequest addEmployeeRequest){
       return employeeService.addEmployee(addEmployeeRequest);
    }

    @GetMapping("/employees/tax-deduction")
    public ResponseEntity<List<EmployeeTaxDetailsResponse>> getEmployeesTaxDeduction() {
        return employeeService.getEmployeesTaxDeduction();

    }

}
