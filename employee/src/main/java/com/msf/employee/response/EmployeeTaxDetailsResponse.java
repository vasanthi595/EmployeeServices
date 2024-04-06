package com.msf.employee.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTaxDetailsResponse {

        private String employeeId;
        private String firstName;
        private String lastName;
        private BigDecimal yearlySalary;
        private BigDecimal taxAmount;
        private BigDecimal cessAmount;
}
