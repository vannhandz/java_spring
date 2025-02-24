package com.techzen.academy_n1224.test.service.impl;

import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.IStudentRepository;
import com.techzen.academy_n1224.test.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Scope("singleton")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {

    IStudentRepository studentRepository;


    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findById( int id) {
       return studentRepository.findById(id);
    }

    public Student create(Student student) {
       return studentRepository.create(student);
    }

    public Student delete(int id) {
       return studentRepository.delete(id);
    }

}
