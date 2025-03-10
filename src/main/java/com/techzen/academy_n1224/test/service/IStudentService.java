package com.techzen.academy_n1224.test.service;

import com.techzen.academy_n1224.test.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
     public Page<Student> findByName(String name, Double scoreAfter, Double scoreBefore, Pageable pageable);
     Student findById( int id);
     Student save(Student student) ;

     void deleteById(int id) ;
}
