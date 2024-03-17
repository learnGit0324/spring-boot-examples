package com.neo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmpRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        List<Employee> result = jdbcTemplate.query(
                "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID FROM HR.EMPLOYEES",
                new UserRowMapper()
        );
        return result;
    }

    public List<Employee> findById(String key) {
        List<Employee> result = jdbcTemplate.query(String.format(
                "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = %s", Long.valueOf(key)),
                new UserRowMapper()
        );
        return result;
    }
    public class UserRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getBigDecimal("EMPLOYEE_ID"));
            employee.setFirstName(rs.getString("FIRST_NAME"));
            employee.setLastName(rs.getString("LAST_NAME"));
            employee.setEmail(rs.getString("EMAIL"));
            employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            employee.setHireDate(rs.getDate("HIRE_DATE"));
            employee.setJobId(rs.getString("JOB_ID"));
            employee.setSalary(rs.getBigDecimal("SALARY"));
            employee.setCommissionPct(rs.getBigDecimal("COMMISSION_PCT"));
            employee.setManagerId(rs.getBigDecimal("MANAGER_ID"));
            employee.setDepartmentId(rs.getBigDecimal("DEPARTMENT_ID"));
            return employee;
        }
    }

}
