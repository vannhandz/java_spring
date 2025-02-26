package com.techzen.academy_n1224.employees.repository;

import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    public List<?> finAttributes(EmployeeSearchRequest employeeSearchRequest);

    public Employee findById(int id);

    public Employee save(Employee employee);

    public Employee deleteEmployees(int id);
}
