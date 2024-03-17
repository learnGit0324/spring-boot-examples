package com.neo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpRepository2 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    public List<Employee> findAll() {
        List<Employee> result = jdbcTemplate.query(
                "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID FROM HR.EMPLOYEES",
                new UserRowMapper()
        );
        return result;
    }

    public List<Employee> findById(String key) throws SQLException {
//        List<Employee> result = jdbcTemplate.query(String.format(
//                "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = %s", Long.valueOf(key)),
//                new UserRowMapper()
//        );
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID FROM HR.EMPLOYEES WHERE EMPLOYEE_ID = %s", Long.valueOf(key)));
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getBigDecimal("EMPLOYEE_ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employeeList.add(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            connection.close();
        }
//        return result;
        return employeeList;
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
