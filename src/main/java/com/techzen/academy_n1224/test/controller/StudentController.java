package com.techzen.academy_n1224.test.controller;


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
@RequiredArgsConstructor
@Scope("singleton")
    @RequestMapping("/student")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    IStudentService studentService ;

    @GetMapping()
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name,Double scoreAfter,Double scoreBefore) {
        if (name == null) {
            throw  new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.builder().
                data(studentService.findByName(name,scoreAfter,scoreBefore)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentsId(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw  new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.builder().
                data(studentService.findById(id)).build());
    }

    @PostMapping()
    public ResponseEntity<Student> add(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.create(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> upd(@PathVariable("id") int id,
                                       @RequestBody Student student) {
        if(studentService.findById(id) == null) {
            throw  new ApiException(ErrorCode.Employees_NOT_EXIST);
        }

        student.setId(id);
        return ResponseEntity.ok(studentService.create(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> del(@PathVariable("id") int id) {
        if(studentService.findById(id) == null) {
            throw  new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


