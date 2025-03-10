package com.techzen.academy_n1224.test.controller;



import com.techzen.academy_n1224.test.dto.page.PageResponse;
import com.techzen.academy_n1224.test.dto.student.StudentRequest;

import com.techzen.academy_n1224.test.en.ApiException;
import com.techzen.academy_n1224.test.en.ApiResponse;
import com.techzen.academy_n1224.test.en.ErrorCode;
import com.techzen.academy_n1224.test.mapper.IStudentMapper;

import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Scope("singleton")
@RequestMapping("/student")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    IStudentService studentService;
    IStudentMapper studentMapper;

    @GetMapping()
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name, Double scoreAfter, Double scoreBefore, Pageable pageable) {
        if (name == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.builder().
                data(new PageResponse<>(studentService.findByName(name, scoreAfter, scoreBefore, pageable).
                        map(studentMapper ::studentToStudentResponse))).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentsId(@PathVariable("id") int id) {

        Student student = studentService.findById(id);
        if (student == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        // chuyển đổi entity sang dto response

        return ResponseEntity.ok(ApiResponse.builder().
                data(studentMapper.studentToStudentResponse(student)).build());
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody StudentRequest studentRequest) {
        Student student = studentMapper.studentResquestToStudent(studentRequest);

        student = studentService.save(student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentMapper.studentToStudentResponse(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> upd(@PathVariable("id") int id,
                                       @RequestBody Student student) {
        if (studentService.findById(id) == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }

        student.setId(id);
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> del(@PathVariable("id") int id) {
        if (studentService.findById(id) == null) {
            throw new ApiException(ErrorCode.Employees_NOT_EXIST);
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


