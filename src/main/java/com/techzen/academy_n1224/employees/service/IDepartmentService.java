package com.techzen.academy_n1224.employees.service;

import com.techzen.academy_n1224.employees.model.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    public List<?> getAll();

    public Optional<Department> findById(int id);

    public Department save(Department department);

    public void delete(int id);
}
