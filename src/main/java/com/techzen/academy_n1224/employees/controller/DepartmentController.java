package com.techzen.academy_n1224.employees.controller;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ApiResponse;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.employees.en.JsonResponse;
import com.techzen.academy_n1224.employees.model.Department;
import com.techzen.academy_n1224.employees.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    IDepartmentService departmentService;

    @GetMapping()
    private ResponseEntity<?> getAll() {
        return JsonResponse.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getID(@PathVariable("id") int id) {
        if(departmentService.findById(id)==null){
            throw new ApiException(ErrorCode.Department_NOT_EXIST);
        }
        return JsonResponse.ok(departmentService.findById(id));
    }

    @PostMapping()
    private ResponseEntity<?> create(@RequestBody Department department) {
        return JsonResponse.created(departmentService.save(department));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> upd(@RequestBody Department department,
                                  @PathVariable("id") int id) {
        if(departmentService.findById(id)==null){
            throw new ApiException(ErrorCode.Department_NOT_EXIST);
        }
        department.setId(id);
        return JsonResponse.ok(departmentService.save(department));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(departmentService.findById(id)==null){
            throw new ApiException(ErrorCode.Department_NOT_EXIST);
        }
        departmentService.delete(id);
        return JsonResponse.noContent();
    }
}
