package com.techzen.academy_n1224.test.repository.impl;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {

    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "nhan", 7),
                    new Student(2, "tuan", 7),
                    new Student(3, "tu", 7)
            )
    );

    public List<Student> findByName(String name) {
        List<Student> stSearch = new ArrayList<>();
        for (Student st : students) {
            if (st.getName().contains(name)) {
                stSearch.add(st);
            }
        }
        return stSearch;
    }

    public Student findById( int id) {
        for (Student st : students) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public Student create(Student student) {
        for (Student st : students) {
            if (st.getId() == student.getId()) {
                st.setName(student.getName());
                st.setScore(student.getScore());
                return st;
            }
        }
        student.setId(students.size() + 1);
        students.add(student);
        return student;
    }

    public Student delete(int id) {
        for (Student st : students) {
            if (st.getId() == id) {
                students.remove(st);
                return st;
            }
        }
        throw new ApiException(ErrorCode.Employees_NOT_EXIST);
    }

}
