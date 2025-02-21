package com.techzen.academy_n1224.employees.employees;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ApiResponse;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentCtrl {
    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Quản Lý"),
                    new Department(2, "Nhân Viên"),
                    new Department(3, "Giám Đốc")
            )
    );

    @GetMapping()
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<?> getID(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.Department_NOT_EXIST));
    }

    @PostMapping("/add")
    private ResponseEntity<?> create(@RequestBody Department department) {
        department.setId(departments.size() + 1);
        departments.add(department);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/upd/{id}")
    private ResponseEntity<?> upd(@RequestBody Department department,
                                  @PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    d.setName(department.getName());
                    return ResponseEntity.ok(d);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.Department_NOT_EXIST));
    }

    @DeleteMapping("/del/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    departments.remove(d);
                    return ResponseEntity.ok(ApiResponse.<Department>builder().message("đã xóa").build());
                })
                .orElseThrow(() -> new ApiException(ErrorCode.Department_NOT_EXIST));
    }
}
