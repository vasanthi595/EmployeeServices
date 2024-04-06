package com.msf.employee.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddEmployeeRequest {

    @NotBlank(message = "employeeId is required/please provide employeeId")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Only alphanumeric characters allowed")
    private String employeeId;

    @NotBlank(message = "firstName is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabetic characters allowed")
    private String firstName;

    @NotBlank(message = "lastName is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabetic characters allowed")
    private String lastName;

    @NotBlank(message = "email is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only alphabetic characters allowed")
    private String email;

    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits long")
    private String phoneNumber;

//    @NotBlank(message = "doj is required")
    private LocalDate doj;

    @NotBlank(message = "salary is required")
//    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
//    @DecimalMax(value = "1000000.0", message = "Salary cannot exceed 1,000,000")
    private BigDecimal salary;
}
