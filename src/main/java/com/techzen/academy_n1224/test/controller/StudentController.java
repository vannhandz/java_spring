package com.techzen.academy_n1224.test.controller;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ApiResponse;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@Scope("singleton")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    IStudentService studentService ;

    @GetMapping("/student")
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(ApiResponse.builder().
                data(studentService.findByName(name)).build());
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudentsId(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw  new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.builder().
                data(student).build());
    }

    @PostMapping("/addst")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.create(student));
    }

    @PutMapping("/updst/{id}")
    public ResponseEntity<Student> upd(@PathVariable("id") int id,
                                       @RequestBody Student student) {
        student.setId(id);
        return ResponseEntity.ok(studentService.create(student));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> del(@PathVariable("id") int id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


