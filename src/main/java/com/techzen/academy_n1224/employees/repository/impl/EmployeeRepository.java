package com.techzen.academy_n1224.employees.repository.impl;

import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

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

    public Employee findById(int id) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT * FROM employee WHERE id_employee = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return Employee.builder()
                        .id(rs.getInt("id_employee"))
                        .name(rs.getString("name"))
                        .birth(rs.getDate("birth").toLocalDate())
                        .gender(rs.getString("gender"))
                        .salary(rs.getDouble("salary"))
                        .phone(rs.getString("phone"))
                        .departmentId(rs.getInt("id_department"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Employee save(Employee employee) {
        if (findById(employee.getId()) == null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("insert into employee(name, birth, gender, salary, phone, id_department)values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getName());
                ps.setDate(2, Date.valueOf(employee.getBirth()));
                ps.setString(3, employee.getGender());
                ps.setDouble(4, employee.getSalary());
                ps.setString(5, employee.getPhone());
                ps.setInt(6, employee.getDepartmentId());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("update  employee set name=? , birth=?, gender=?, salary=?, phone=?, id_department=? where id_employee=?");
                ps.setString(1, employee.getName());
                ps.setDate(2, Date.valueOf(employee.getBirth()));
                ps.setString(3, employee.getGender());
                ps.setDouble(4, employee.getSalary());
                ps.setString(5, employee.getPhone());
                ps.setInt(6, employee.getDepartmentId());
                ps.setInt(7, employee.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public Employee deleteEmployees(int id) {
        Employee employee = findById(id);
        if (employee != null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("delete from employee where id_employee = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return employee;
        }
        return null;
    }
}
