package com.techzen.academy_n1224.employees.controller;
import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.employees.en.JsonResponse;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController  {

    IEmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?> getAllEmployees(EmployeeSearchRequest employeeSearchRequest) {
           return JsonResponse.ok(employeeService.finAttributes(employeeSearchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployees(@PathVariable("id") int id) {
        if(employeeService.findById(id) == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        return JsonResponse.ok(employeeService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> addEmployees(@RequestBody Employee employee) {
       return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updEmployees(@RequestBody Employee employee,
                                          @PathVariable("id") int id) {
        if(employeeService.findById(id) == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployees(@PathVariable("id") int id) {
        if(employeeService.findById(id) == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        employeeService.deleteEmployees(id);
        return JsonResponse.noContent();
    }

}
