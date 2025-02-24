package com.techzen.academy_n1224.employees.repository.impl;

import com.techzen.academy_n1224.employees.model.Department;
import com.techzen.academy_n1224.employees.repository.IDepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepository implements IDepartmentRepository {

    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Quản Lý"),
                    new Department(2, "Nhân Viên"),
                    new Department(3, "Giám Đốc")
            )
    );

    public List<?> getAll() {
        return departments;
    }

    public Optional<Department> findById(int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
    }

    public Department save(Department department) {
        return findById(department.getId())
                .map(d -> {
                    d.setName(department.getName());
                    return d;
                })
                .orElseGet(() -> {
                    department.setId(departments.size() + 1);
                    departments.add(department);
                    return department;
                });
    }

    public void delete(int id) {
        findById(id)
                .map(d -> {
                    departments.remove(d);
                    return d;
                });
    }
}
