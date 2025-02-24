package com.techzen.academy_n1224.employees.repository.impl;

import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "van nhan", LocalDate.of(2003, 10, 3), "nam", 5000000.0, "0762605901", 1),
                    new Employee(2, "anh tu", LocalDate.of(2003, 10, 20), "nam", 15000000.0, "0762605902", 2),
                    new Employee(3, "tuan", LocalDate.of(2003, 6, 19), "nu", 25000000.0, "0762605903", 3)
            )
    );

    public List<?> finAttributes(EmployeeSearchRequest employeeSearchRequest) {

        return employees.stream()
                .filter(e -> (employeeSearchRequest.getName() == null || e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase())))
                .filter(e -> (employeeSearchRequest.getDobFrom() == null || !e.getBirth().isBefore(employeeSearchRequest.getDobFrom())))
                .filter(e -> (employeeSearchRequest.getDobTo() == null || !e.getBirth().isAfter(employeeSearchRequest.getDobTo())))
                .filter(e -> (employeeSearchRequest.getGender() == null || e.getGender().equals(employeeSearchRequest.getGender())))
                .filter(e -> (employeeSearchRequest.getPhone() == null || e.getPhone().contains(employeeSearchRequest.getPhone())))
                .filter(e -> (employeeSearchRequest.getDepartmentId() == null || Objects.equals(e.getDepartmentId(), employeeSearchRequest.getDepartmentId())))
                .filter(e -> {

                    if (employeeSearchRequest.getSalaryRange() == null) {
                        return true;
                    }
                    return switch (employeeSearchRequest.getSalaryRange()) {
                        case "5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() <= 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "20" -> e.getSalary() >= 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
    }

    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public Employee save(Employee employee) {
        return findById(employee.getId())
                .map(e -> {
                    e.setName(employee.getName());
                    e.setBirth(employee.getBirth());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return e;
                })
                .orElseGet(() -> {
                    employee.setId(employees.size() + 1);
                    employees.add(employee);
                    return employee;
                });
    }

    public void deleteEmployees(int id) {
        findById(id)
                .map(e -> {
                    employees.remove(e);
                    return e;
                });
    }
}
