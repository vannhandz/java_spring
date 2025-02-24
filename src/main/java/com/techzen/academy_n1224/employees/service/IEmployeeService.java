package com.techzen.academy_n1224.employees.service;

import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    public List<?> finAttributes(EmployeeSearchRequest employeeSearchRequest);

    public Optional<Employee> findById(int id);

    public Employee save(Employee employee);

    public void deleteEmployees(int id);


}
