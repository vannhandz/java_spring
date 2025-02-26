package com.techzen.academy_n1224.employees.service.impl;


import com.techzen.academy_n1224.employees.model.Department;
import com.techzen.academy_n1224.employees.repository.IDepartmentRepository;
import com.techzen.academy_n1224.employees.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DepartmentService implements IDepartmentService {

    IDepartmentRepository departmentRepository;

    public List<?> getAll() {
        return departmentRepository.getAll();
    }

    public Department findById(int id) {
        return departmentRepository.findById(id);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department delete(int id) {
        return  departmentRepository.delete(id);
    }

}
