package com.techzen.academy_n1224.employees.service.impl;

import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.repository.IEmployeeRepository;
import com.techzen.academy_n1224.employees.service.IEmployeeService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class EmployeeService implements IEmployeeService {

    IEmployeeRepository employeeRepository;

    public List<?> finAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.finAttributes(employeeSearchRequest);
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployees(int id) {
        employeeRepository.deleteEmployees(id);
    }


}
