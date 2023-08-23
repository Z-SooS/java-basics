package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoDB implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Employee> getAllEmployees() {
        final String GET_ALL_EMPLOYEES = "SELECT * FROM employee";
        return jdbc.query(GET_ALL_EMPLOYEES, new EmployeeMapper());
    }

    @Override
    public Employee getEmployeeById(int id) {
        try {
            final String GET_BY_ID = "SELECT * FROM employee WHERE id = ?";
            return jdbc.queryForObject(GET_BY_ID, new EmployeeMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Transactional
    @Override
    public Employee addEmployee(Employee employee) {
        final String ADD_EMPLOYEE = "INSERT INTO employee(firstName, lastName) VALUES(?,?);";
        jdbc.update(ADD_EMPLOYEE, employee.getFirstName(), employee.getLastName());

        int id = jdbc.queryForObject("SELECT currval('employee_id_seq)", Integer.class);
        employee.setId(id);
        return employee;
    }

    @Override
    public void updateEmployee(Employee employee) {
        final String UPDATE_EMPLOYEE = "UPDATE employee SET firstName = ?, lastName = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_EMPLOYEE,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getId());
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE employeeId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);

        final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
        jdbc.update(DELETE_EMPLOYEE, id);
    }

    public static class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));
            return emp;
        }
    }
}
