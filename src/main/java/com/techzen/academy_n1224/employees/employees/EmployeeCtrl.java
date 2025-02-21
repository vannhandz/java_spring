package com.techzen.academy_n1224.employees.employees;

import com.techzen.academy_n1224.employees.en.ApiResponse;
import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;

import com.techzen.academy_n1224.employees.en.JsonResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeCtrl {

    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "van nhan", LocalDate.of(2003, 10, 3), "nam", 5000000.0, "0762605901", 1),
                    new Employee(2, "anh tu", LocalDate.of(2003, 10, 20), "nam", 15000000.0, "0762605902", 2),
                    new Employee(3, "tuan", LocalDate.of(2003, 6, 19), "nu", 25000000.0, "0762605903", 3)
            )
    );

    @GetMapping()
    public ResponseEntity<?> getAllEmployees(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(defaultValue = "dobFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(defaultValue = "dobTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "salary", required = false) String salary,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "departmentId", required = false) Integer departmentId) {
        List<?> listEmployees = employees.stream()
                .filter(e -> (name == null || e.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(e -> (dobFrom == null || !e.getBirth().isBefore(dobFrom)))
                .filter(e -> (dobTo == null || !e.getBirth().isAfter(dobTo)))
                .filter(e -> (gender == null || e.getGender().equals(gender)))
                .filter(e -> (phone == null || e.getPhone().contains(phone)))
                .filter(e -> (departmentId == null || Objects.equals(e.getDepartmentId(), departmentId)))
                .filter(e -> {
                    if (salary == null) {
                        return true;
                    }
                    return switch (salary) {
                        case "5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() <= 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "20" -> e.getSalary() >= 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());

        return JsonResponse.ok(listEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployees(@PathVariable("id") int id) {

        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.Employees_NOT_EXIST));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployees(@RequestBody Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
        return ResponseEntity.ok(ApiResponse.<Employee>builder().data(employee).build());
    }

    @PutMapping("upd/{id}")
    public ResponseEntity<?> updEmployees(@RequestBody Employee employee,
                                          @PathVariable("id") int id) {

        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setBirth(employee.getBirth());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return ResponseEntity.ok(e);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.Employees_NOT_EXIST));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteEmployees(@PathVariable("id") int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return ResponseEntity.ok(ApiResponse.<Employee>builder().message("da xoa").build());
                })
                .orElseThrow(() -> new ApiException(ErrorCode.Employees_NOT_EXIST));
    }

}
