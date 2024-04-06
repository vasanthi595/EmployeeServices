package com.msf.employee.service.Impl;

import com.msf.employee.entities.Employee;
import com.msf.employee.exception.EmployeeGenericException;
import com.msf.employee.repository.EmployeeRepository;
import com.msf.employee.request.AddEmployeeRequest;
import com.msf.employee.response.EmployeeTaxDetailsResponse;
import com.msf.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Object> addEmployee(@Valid AddEmployeeRequest addEmployeeRequest) {

        Employee employee = Employee.builder()
                .employeeId(addEmployeeRequest.getEmployeeId())
                .firstName(addEmployeeRequest.getFirstName())
                .lastName(addEmployeeRequest.getLastName())
                .email(addEmployeeRequest.getEmail())
                .phoneNumber(addEmployeeRequest.getPhoneNumber())
                .doj(addEmployeeRequest.getDoj())
                .salary(addEmployeeRequest.getSalary())
                .build();
        try {
            employeeRepository.save(employee);
        }catch (Exception e){
            throw  new EmployeeGenericException(1001,"Error While saving");
        }
      return  ResponseEntity.accepted().body("Data Inserted successfully");
    }

    @Override
    public ResponseEntity<List<EmployeeTaxDetailsResponse>> getEmployeesTaxDeduction() {

        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeTaxDetailsResponse> employeesTaxDetails = employees.stream()
                .map(employee -> {
                    BigDecimal taxAmount = calculateTax(employee.getSalary(), employee.getDoj());
                    // Calculate cess
                    BigDecimal cessAmount = BigDecimal.ZERO;
                    if (taxAmount.compareTo(BigDecimal.valueOf(2500000)) > 0) {
                        BigDecimal excessAmount = taxAmount.subtract(BigDecimal.valueOf(2500000));
                        cessAmount = excessAmount.multiply(BigDecimal.valueOf(0.02));
                    }
                    return EmployeeTaxDetailsResponse.builder()
                            .employeeId(employee.getEmployeeId())
                            .firstName(employee.getFirstName())
                            .lastName(employee.getLastName())
                            .yearlySalary(employee.getSalary().multiply(BigDecimal.valueOf(12)))
                            .taxAmount(taxAmount)
                            .cessAmount(cessAmount).build();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(employeesTaxDetails);
    }

    private BigDecimal calculateTax(BigDecimal salary, LocalDate doj) {

        LocalDate endOfFinancialYear = LocalDate.of(doj.getYear() + 1, 3, 31);
        int numberOfMonthsWorked = (int) Math.max(0, Math.min(12, endOfFinancialYear.getMonthValue() - doj.getMonthValue() + 1));

        // Adjust salary
        BigDecimal adjustedSalary = salary.multiply(BigDecimal.valueOf(numberOfMonthsWorked));

        // Calculate tax
        BigDecimal tax = BigDecimal.ZERO;
        if (adjustedSalary.compareTo(BigDecimal.valueOf(250000)) > 0) {
            BigDecimal taxableAmount = adjustedSalary.subtract(BigDecimal.valueOf(250000));
            if (taxableAmount.compareTo(BigDecimal.valueOf(250000)) <= 0) {
                tax = taxableAmount.multiply(BigDecimal.valueOf(0.05));
            } else if (taxableAmount.compareTo(BigDecimal.valueOf(500000)) <= 0) {
                tax = BigDecimal.valueOf(12500).add(taxableAmount.subtract(BigDecimal.valueOf(250000)).multiply(BigDecimal.valueOf(0.1)));
            } else {
                tax = BigDecimal.valueOf(37500).add(taxableAmount.subtract(BigDecimal.valueOf(500000)).multiply(BigDecimal.valueOf(0.2)));
            }
        }
        return tax.setScale(2, RoundingMode.HALF_UP);
    }

}
