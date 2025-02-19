package com.techzen.academy_n1224;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping()
public class StudentController {


    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "nhan", 7),
                    new Student(2, "tuan", 7),
                    new Student(3, "tu", 7)
            )
    );

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudents(@RequestParam(defaultValue = "") String name) {
        List<Student> stSearch = new ArrayList<>();
        for (Student st : students) {
            if (st.getName().contains(name)) {
                stSearch.add(st);
            }
        }
        return ResponseEntity.ok(stSearch);
    }

    @PostMapping("/addst")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        student.setId((int) (Math.random() * 10000));
        students.add(student);
        return ResponseEntity.status(201).body(student);
    }

    @PutMapping("/updst/{id}")
    public ResponseEntity<Student> upd(@PathVariable("id") int id,
                                       @RequestBody Student student) {
        for (Student st : students) {
            if (st.getId() == id) {
                st.setName(student.getName());
                st.setScore(student.getScore());
                return ResponseEntity.ok(student);

            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> del(@PathVariable("id") int id) {
        for (Student st : students) {
            if (st.getId() == id) {
                students.remove(st);
                return ResponseEntity.ok(st);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


