package com.techzen.academy_n1224.test.service.impl;

import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.IStudentRepository;
import com.techzen.academy_n1224.test.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Scope("singleton")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {

    IStudentRepository studentRepository;


    public Page<Student> findByName(String name, Double scoreAfter, Double scoreBefore, Pageable pageable) {
        return studentRepository.findAttribute(name, scoreAfter, scoreBefore,pageable);
    }

    public Student findById( int id) {
       return studentRepository.findById(id);
    }

    public Student save(Student student) {
       return studentRepository.save(student);
    }


    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

}
