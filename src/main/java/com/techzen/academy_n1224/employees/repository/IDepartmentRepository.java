package com.techzen.academy_n1224.employees.repository;

import com.techzen.academy_n1224.employees.model.Department;


import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository {

    public List<?> getAll();

    public Department findById(int id);

    public Department save(Department department);

    public Department delete(int id);
}
