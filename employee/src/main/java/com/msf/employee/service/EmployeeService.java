package com.msf.employee.service;

import com.msf.employee.request.AddEmployeeRequest;
import com.msf.employee.response.EmployeeTaxDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    ResponseEntity<Object> addEmployee(@Valid AddEmployeeRequest addEmployeeRequest);

    ResponseEntity<List<EmployeeTaxDetailsResponse>> getEmployeesTaxDeduction();
}
