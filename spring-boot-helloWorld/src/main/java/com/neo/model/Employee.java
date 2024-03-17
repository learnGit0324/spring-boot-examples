package com.neo.model;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class Employee {
    public BigDecimal employeeId;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public Date hireDate;
    public String jobId;
    public BigDecimal salary;
    public BigDecimal commissionPct;
    public BigDecimal managerId;
    public BigDecimal departmentId;
}
